package com.tosak.lately.features.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.tosak.lately.components.LatelyTopBar
import com.tosak.lately.navigation.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController) {

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
      mapViewportState = rememberMapViewportState {
        setCameraOptions {
          zoom(2.0)
          center(Point.fromLngLat(-98.0, 39.5))
          pitch(0.0)
          bearing(0.0)
        }
      },
      scaleBar = {
        ScaleBar(Modifier.padding(top = 60.dp))
      },
      logo = {
        Logo(Modifier.padding(bottom = 55.dp))
      },
      attribution = {
        Attribution(Modifier.padding(bottom = 55.dp))
      }
    )
  }
}