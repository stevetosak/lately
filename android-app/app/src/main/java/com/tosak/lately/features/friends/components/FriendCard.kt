package com.tosak.lately.features.friends.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tosak.lately.features.friends.Friend

@Composable
fun FriendCard(
  friend: Friend,
  onMessageClick: () -> Unit,
  onRemoveClick: () -> Unit,
  onBlockClick: () -> Unit
) {
  Surface(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    color = MaterialTheme.colorScheme.surface,
    tonalElevation = 0.dp
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      FriendAvatar(friend.displayName, avatarUrl = friend.avatarUrl)

      FriendIdentity(
        displayName = friend.displayName,
        username = friend.username,
        modifier = Modifier.weight(1f)
      )

      OutlinedButton(
        onClick = onMessageClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
        modifier = Modifier.height(36.dp)
      ) {
        Text("Message", fontSize = 13.sp, fontWeight = FontWeight.Medium)
      }

      FriendCardMenu(
        onRemoveClick = onRemoveClick,
        onBlockClick = onBlockClick
      )
    }
  }
}

@Composable
private fun FriendIdentity(
  displayName: String,
  username: String,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Text(
      text = displayName,
      style = MaterialTheme.typography.bodyLarge,
      fontWeight = FontWeight.SemiBold,
      color = MaterialTheme.colorScheme.onBackground
    )
    Text(
      text = username,
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )
  }
}

@Composable
fun FriendCardMenu(
  onRemoveClick: () -> Unit,
  onBlockClick: () -> Unit
) {
  var menuExpanded by remember { mutableStateOf(false) }

  Box {
    IconButton(
      onClick = { menuExpanded = true },
      modifier = Modifier.size(36.dp)
    ) {
      Icon(
        imageVector = Icons.Outlined.MoreVert,
        contentDescription = "More options",
        modifier = Modifier.size(20.dp),
        tint = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }

    DropdownMenu(
      expanded = menuExpanded,
      onDismissRequest = { menuExpanded = false },
      shape = RoundedCornerShape(14.dp)
    ) {
      DropdownMenuItem(
        text = { Text("Remove friend", fontWeight = FontWeight.Medium) },
        leadingIcon = { Icon(Icons.Outlined.PersonRemove, contentDescription = null) },
        onClick = {
          menuExpanded = false
          onRemoveClick()
        }
      )
      DropdownMenuItem(
        text = {
          Text(
            "Block",
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.error
          )
        },
        leadingIcon = {
          Icon(
            Icons.Outlined.Block,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error
          )
        },
        onClick = {
          menuExpanded = false
          onBlockClick()
        }
      )
    }
  }
}