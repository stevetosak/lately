package com.tosak.lately.features.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tosak.lately.R
import com.tosak.lately.features.profile.ProfileUiState

@Composable
fun ProfileHeader(
  uiState: ProfileUiState,
  onFriendsClick: () -> Unit
) {
  ProfileAvatar(size = 96) {
    AsyncImage(
      model = uiState.avatarUrl,
      contentDescription = "${uiState.displayName} avatar",
      modifier = Modifier
        .fillMaxSize()
        .clip(CircleShape),
      contentScale = ContentScale.Crop,
      placeholder = painterResource(R.drawable.ic_placeholder),
      error = painterResource(R.drawable.ic_fallback)
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