package com.tosak.lately.features.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tosak.lately.R

@Composable
fun UserMarker(
    avatarUrl: String?,
    username: String,
    isCurrentUser: Boolean = false,
    onClick: () -> Unit = {}
) {
    val storyRingBrush = Brush.sweepGradient(
        colors = listOf(
            Color(0xFFE1306C),
            Color(0xFFF77737),
            Color(0xFFE1306C),
        )
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(58.dp)
            .clickable { onClick() }
    ) {
        if (isCurrentUser) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawCircle(
                            brush = storyRingBrush,
                            radius = size.minDimension / 2,
                            style = Stroke(width = 3.dp.toPx())
                        )
                    }
            )
        }

        Box(
            modifier = Modifier
                .size(if (isCurrentUser) 20.dp else 48.dp)
                .background(Color.White, CircleShape)
        )

        AsyncImage(
            model = if(isCurrentUser) R.mipmap.current_user_icon_round else avatarUrl,
            contentDescription = username,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )

        if (isCurrentUser) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.BottomEnd)
                    .background(Color(0xFFE1306C), CircleShape)
                    .border(1.5.dp, Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(10.dp)
                )
            }
        }
    }
}