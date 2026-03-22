package com.tosak.lately.features.map

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.mapbox.geojson.Point
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.tosak.lately.core.ui.components.bars.LatelyTopBar
import com.tosak.lately.features.map.components.CameraMode
import com.tosak.lately.features.map.components.CameraModeButton
import com.tosak.lately.features.map.components.CurrentLocationButton
import com.tosak.lately.features.map.components.UserLocationEffect
import com.tosak.lately.features.map.data.testUser
import com.tosak.lately.features.stories.StoryViewModel
import com.tosak.lately.features.stories.components.StoryEffect
import com.tosak.lately.navigation.Destinations

@RequiresApi(Build.VERSION_CODES.O)
@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(navController: NavController) {
    val mapViewModel: MapViewModel = hiltViewModel()
    val storyViewModel: StoryViewModel = hiltViewModel()
    val userLocation by mapViewModel.userLocation.collectAsStateWithLifecycle()
    var currentCameraMode by remember { mutableStateOf(CameraMode.STREET) }

    var currentCameraOptions by remember { mutableStateOf(cameraOptions {
        zoom(2.0)
        pitch(0.0)
        bearing(0.0)
    }) }

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(2.0)
            center(Point.fromLngLat(-98.0, 39.5))
            pitch(0.0)
            bearing(0.0)
        }
    }

    LaunchedEffect(mapViewportState.cameraState) {
        currentCameraOptions = cameraOptions {
            zoom(mapViewportState.cameraState?.zoom)
            pitch(mapViewportState.cameraState?.pitch)
            bearing(mapViewportState.cameraState?.bearing)
            center(mapViewportState.cameraState?.center)
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MapboxMap(
                Modifier.fillMaxSize(),
                mapViewportState = mapViewportState,
                scaleBar = { ScaleBar(Modifier.padding(top = 60.dp)) },
                logo = { Logo(Modifier.padding(bottom = 55.dp)) },
                compass = {}
            ) {
                UserLocationEffect(userLocation, testUser)
                StoryEffect(
                    userLocation = userLocation,
                    storyViewModel = storyViewModel,
                    navController = navController
                )
                MapLocationEffect(mapViewModel = mapViewModel, mapViewportState = mapViewportState)
            }

            if (userLocation == null) {
                Surface(color = MaterialTheme.colorScheme.background.copy(alpha = 0.6f)) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator();
                    }
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CurrentLocationButton(flyToCurrentLocation = {
                    userLocation?.let {
                        val point = Point.fromLngLat(it.longitude,it.latitude)
                        mapViewportState.flyTo(
                            cameraOptions {
                            center(point)
                            zoom(CameraMode.OVERVIEW.zoom)
                            pitch(CameraMode.OVERVIEW.pitch)
                            bearing(currentCameraOptions.bearing)
                        })
                    }

                })
                CameraModeButton(
                    currentMode = currentCameraMode,
                    onModeSelected = { mode ->
                        currentCameraMode = mode
                        mapViewportState.flyTo(
                            cameraOptions {
                                pitch(mode.pitch)
                                zoom(mode.zoom)
                            }
                        )
                    }
                )
            }
        }
    }
}