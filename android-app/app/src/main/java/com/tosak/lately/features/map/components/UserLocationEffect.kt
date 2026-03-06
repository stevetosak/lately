package com.tosak.lately.features.map.components

import User
import android.location.Location
import androidx.compose.runtime.Composable
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions

fun onUserClick(id: String){}

@Composable
fun UserLocationEffect(userLocation: Location?,user: User){
        userLocation?.let { location ->
        val point = Point.fromLngLat(location.longitude, location.latitude)
            ViewAnnotation(
                options = viewAnnotationOptions {
                    geometry(point)
                    allowOverlap(true)
                }
            ) {
                UserMarker(
                    avatarUrl = user.avatarUrl,
                    username = user.username,
                    isCurrentUser = true,
                    onClick = { onUserClick(user.id) }
                )
            }
    }
}