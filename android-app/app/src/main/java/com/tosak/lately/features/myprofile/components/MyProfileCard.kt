package com.tosak.lately.features.myprofile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.tosak.lately.core.ui.components.avatar.AvatarRing
import com.tosak.lately.core.ui.components.avatar.Avatar
import com.tosak.lately.features.myprofile.MyProfileUiState

@Composable
fun MyProfileCard(
  uiState: MyProfileUiState,
  onFriendsClick: () -> Unit
) {
  AvatarRing(size = 96) {
    Avatar(
      name = "${uiState.displayName} avatar",
      avatarUrl = uiState.avatarUrl
    )
  }
  Spacer(Modifier.height(20.dp))
  Text(
    text = uiState.displayName.ifBlank { "Your Name" },
    style = MaterialTheme.typography.headlineSmall,
    fontWeight = FontWeight.Bold,
    color      = MaterialTheme.colorScheme.onBackground
  )
  Spacer(Modifier.height(4.dp))
  Text(
    text  = uiState.username.ifBlank { "@username" },
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurfaceVariant
  )
  Spacer(Modifier.height(12.dp))
  Text(
    text = buildAnnotatedString {
      withStyle(SpanStyle(
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Bold,

        )) {
        append("${uiState.friendCount}")
      }
      append(" ")
      withStyle(SpanStyle(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Medium
      )) {
        append(if (uiState.friendCount == 1) "friend" else "friends")
      }
    },
    style   = MaterialTheme.typography.bodyMedium,
    modifier = Modifier.clickable(
      interactionSource = remember { MutableInteractionSource() },
      indication        = null,
      onClick           = onFriendsClick
    )
  )
}