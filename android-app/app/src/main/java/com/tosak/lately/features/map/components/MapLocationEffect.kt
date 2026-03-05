package com.tosak.lately.features.map

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.mapbox.geojson.Point
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapLocationEffect(
    mapViewModel: MapViewModel,
    mapViewportState: MapViewportState
) {
    val userLocation by mapViewModel.userLocation.collectAsStateWithLifecycle()
    val locationPermission = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        mapViewModel.fetchLocation()
    }

    LaunchedEffect(locationPermission.status) {
        when {
            locationPermission.status.isGranted -> mapViewModel.fetchLocation()
            locationPermission.status.shouldShowRationale -> { /* show explanation UI */ }
            else -> locationPermission.launchPermissionRequest()
        }
    }

    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            val point = Point.fromLngLat(location.longitude, location.latitude)
            mapViewportState.flyTo(cameraOptions {
                center(point)
                zoom(17.0)
                pitch(75.0)
            })
        }
    }
}