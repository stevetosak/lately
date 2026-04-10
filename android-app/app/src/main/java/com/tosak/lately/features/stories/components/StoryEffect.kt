package com.tosak.lately.features.stories.components

import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import com.tosak.lately.features.map.components.UserMarker
import com.tosak.lately.features.stories.StoryViewModel
import com.tosak.lately.navigation.Destinations
import androidx.compose.runtime.getValue

@Composable
fun StoryEffect(
    userLocation: Location?,
    storyViewModel: StoryViewModel,
    navController: NavController
) {
    userLocation?.let {
        val uiState by storyViewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(userLocation) {
            storyViewModel.loadNearbyStories(it)
        }

        uiState.stories.forEach { story ->
            val point = Point.fromLngLat(story.location.longitude, story.location.latitude)
            ViewAnnotation(
                options = viewAnnotationOptions {
                    geometry(point)
                    allowOverlap(false)
                }
            ) {
                UserMarker(
                    avatarUrl    = story.authorAvatarUrl,
                    username     = story.authorUsername,
                    isCurrentUser = false,
                    onClick      = {
                        navController.navigate(
                            Destinations.LiveStoryViewer.route(story.id)
                        )
                    }
                )
            }
        }
    }
}