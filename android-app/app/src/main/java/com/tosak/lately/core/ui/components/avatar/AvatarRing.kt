package com.tosak.lately.core.ui.components.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

/**
 * Gradient ring used around the avatar images.
 */
@Composable
fun AvatarRing(
  modifier: Modifier = Modifier,
  size: Int = 96,
  onClick: (() -> Unit)? = null,
  showEditBadge: Boolean = false,
  content: @Composable BoxScope.() -> Unit
) {
  Box(
    modifier = modifier.size(size.dp),
    contentAlignment = Alignment.Center
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(CircleShape)
        .background(
          Brush.sweepGradient(
            listOf(
              MaterialTheme.colorScheme.primary,
              MaterialTheme.colorScheme.tertiary,
              MaterialTheme.colorScheme.primary
            )
          )
        )
    )
    Box(
      modifier = Modifier
        .size((size - 8).dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.surfaceVariant),
      content = content
    )

    if (showEditBadge && onClick != null) {
      Box(
        modifier = Modifier
          .size(28.dp)
          .clip(CircleShape)
          .background(MaterialTheme.colorScheme.primary)
          .clickable(onClick = onClick)
          .align(Alignment.BottomEnd),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector        = Icons.Outlined.CameraAlt,
          contentDescription = "Change avatar",
          modifier           = Modifier.size(14.dp),
          tint               = MaterialTheme.colorScheme.onPrimary
        )
      }
    }
  }
}