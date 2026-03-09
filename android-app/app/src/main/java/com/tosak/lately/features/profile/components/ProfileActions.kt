package com.tosak.lately.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tosak.lately.features.search.FriendshipStatus

@Composable
fun ProfileActions(
  friendshipStatus: FriendshipStatus,
  onSendRequest: () -> Unit,
  onCancelRequest: () -> Unit,
  onUnfriendClick: () -> Unit,
  onMessageClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    when (friendshipStatus) {
      FriendshipStatus.NONE -> {
        GradientButton(
          label = "Add Friend",
          onClick = onSendRequest,
          modifier = Modifier.weight(1f)
        )
      }

      FriendshipStatus.REQUEST_SENT -> {
        DarkButton(
          label = "Requested",
          onClick = onCancelRequest,
          modifier = Modifier.weight(1f)
        )
      }

      FriendshipStatus.FRIENDS -> {
        DarkButton(
          label = "Friends",
          icon = Icons.Outlined.Group,
          onClick = onUnfriendClick,
          modifier = Modifier.weight(1f)
        )
        GradientButton(
          label = "Message",
          onClick = onMessageClick,
          modifier = Modifier.weight(1f)
        )
      }
    }
  }
}

private val ButtonShape = RoundedCornerShape(14.dp)
private val ButtonPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)

@Composable
private fun GradientButton(
  label: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val gradient = Brush.linearGradient(
    colors = listOf(
      MaterialTheme.colorScheme.primary,
      MaterialTheme.colorScheme.tertiary
    )
  )

  Box(
    modifier = modifier
      .clip(ButtonShape)
      .background(gradient)
      .clickable { onClick() }
      .padding(ButtonPadding),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = label,
      fontWeight = FontWeight.SemiBold,
      letterSpacing = 0.sp,
      color = MaterialTheme.colorScheme.onPrimary
    )
  }
}

@Composable
private fun DarkButton(
  label: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  icon: ImageVector? = null
) {
  Box(
    modifier = modifier
      .clip(ButtonShape)
      .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
      .clickable { onClick() }
      .padding(ButtonPadding),
    contentAlignment = Alignment.Center,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      if (icon != null) {
        Icon(
          icon,
          contentDescription = null,
          modifier = Modifier.size(16.dp),
          tint = Color(0xFFF5F5F5)
        )
      }
      Text(
        text = label,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        color = Color(0xFFF5F5F5)
      )
    }
  }
}