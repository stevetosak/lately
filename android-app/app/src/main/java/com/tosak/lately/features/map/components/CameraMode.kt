package com.tosak.lately.features.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class CameraMode(val label: String, val icon: ImageVector, val pitch: Double, val zoom: Double) {
    OVERVIEW("Overview", Icons.Default.ZoomOut, pitch = 0.0, zoom = 13.0),
    STREET("Street", Icons.Default.Navigation, pitch = 60.0, zoom = 18.0),
    TILTED("Tilted", Icons.Default.Explore, pitch = 45.0, zoom = 16.0),
}


@Composable
fun CameraModeButton(
    currentMode: CameraMode,
    onModeSelected: (CameraMode) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier
                .background(Color.White, CircleShape)
                .size(48.dp)
        ) {
            Icon(
                imageVector = currentMode.icon,
                contentDescription = "Camera Mode"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            CameraMode.entries.forEach { mode ->
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(imageVector = mode.icon, contentDescription = null)
                    },
                    text = { Text(mode.label) },
                    onClick = {
                        onModeSelected(mode)
                        expanded = false
                    }
                )
            }
        }
    }
}