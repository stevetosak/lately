package com.tosak.lately.features.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tosak.lately.core.ui.components.avatar.Avatar
import com.tosak.lately.core.ui.components.avatar.AvatarRing
import com.tosak.lately.features.profile.ProfileUser

@Composable
fun ProfileCard(
  user: ProfileUser,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier.fillMaxWidth()
  ) {
    Surface(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
      shape = RoundedCornerShape(20.dp),
      color = MaterialTheme.colorScheme.surface,
      tonalElevation = 1.dp
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column(modifier = Modifier.weight(2f)) {
          Text(
            text = user.displayName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )
          Text(
            text = user.username,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    AvatarRing(
      size = 80,
      modifier = Modifier
        .align(Alignment.TopEnd)
        .offset(x = (-16).dp)
    ) {
      Avatar(
        name = user.displayName,
        avatarUrl = user.avatarUrl,
      )
    }
  }
}

