package com.tosak.lately.features.stories.components

import android.location.Location
import androidx.compose.runtime.Composable
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import com.tosak.lately.features.map.components.UserMarker
import com.tosak.lately.features.stories.Story
import com.tosak.lately.features.stories.StoryViewModel

fun onStoryClick(story: Story){

}

@Composable
fun StoryEffect(userLocation: Location?,storyViewModel: StoryViewModel){
    userLocation?.let {
        val stories = storyViewModel.getNearbyStories(it, 5000)
        stories.forEach { story ->
            val point = Point.fromLngLat(story.location.longitude, story.location.latitude)
            ViewAnnotation(
                options = viewAnnotationOptions {
                    geometry(point)
                    allowOverlap(false)
                }
            ) {
                UserMarker(
                    avatarUrl = story.authorAvatarUrl,
                    username = story.authorUsername,
                    isCurrentUser = false,
                    onClick = { onStoryClick(story) }
                )
            }
        }
    }
}