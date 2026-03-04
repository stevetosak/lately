package com.tosak.lately.features.map

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.mapbox.geojson.Point
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import com.tosak.lately.core.ui.components.LatelyTopBar
import com.tosak.lately.navigation.Destinations

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(navController: NavController) {
    val mapViewModel: MapViewModel = hiltViewModel()
    val userLocation by mapViewModel.userLocation.collectAsStateWithLifecycle()

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(10.0)
            center(Point.fromLngLat(-98.0, 39.5)) // default center while location loads
            pitch(0.0)
            bearing(0.0)
        }
    }

    val locationPermission = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    LaunchedEffect(Unit) {
        mapViewModel.fetchLocation()
    }

    LaunchedEffect(locationPermission.status) {
        when {
            locationPermission.status.isGranted -> {
                mapViewModel.fetchLocation()
            }
            locationPermission.status.shouldShowRationale -> {
                // show explanation UI
            }
            else -> {
                locationPermission.launchPermissionRequest()
            }
        }
    }

    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            val point = Point.fromLngLat(location.longitude, location.latitude)
            mapViewportState.flyTo(
                cameraOptions {
                    center(point)
                    zoom(14.0)
                }
            )
        }
    }





    Scaffold(
        topBar = {
            LatelyTopBar(
                onCameraClick = {},
                onChatClick = {
                    navController.navigate(Destinations.Messages.route)
                }
            )
        }
    ) { paddingValues ->
        MapboxMap(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            mapViewportState = mapViewportState,
            scaleBar = { ScaleBar(Modifier.padding(top = 60.dp)) },
            logo = { Logo(Modifier.padding(bottom = 55.dp)) },
            attribution = { Attribution(Modifier.padding(bottom = 55.dp)) }
        ){
            userLocation?.let { location ->
                val point = Point.fromLngLat(location.longitude, location.latitude)
                CircleAnnotation(point = point) {
                    circleColor = Color.Red
                    circleRadius = 10.0
                    circleStrokeWidth = 2.0
                    circleStrokeColor = Color.White
                }
            }
        }
    }
}