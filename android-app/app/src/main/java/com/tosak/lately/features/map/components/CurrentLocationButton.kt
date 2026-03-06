package com.tosak.lately.features.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tosak.lately.R

@Composable
fun CurrentLocationButton(modifier: Modifier = Modifier,flyToCurrentLocation: () -> Unit) {
    Box(modifier = modifier) {
        IconButton(
            modifier = Modifier
                .background(Color.Gray, CircleShape)
                .size(48.dp),
            onClick = flyToCurrentLocation) {
            Icon(
                painter = painterResource(id = R.drawable.locate_fixed),
                contentDescription = "current location button"
            )
        }
    }
}