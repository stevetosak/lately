package com.tosak.lately.features.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tosak.lately.features.notifications.NotificationsUiState

@Composable
fun NotificationsContent(
    innerPadding: PaddingValues,
    uiState: NotificationsUiState,
    onAcceptFriendRequest: (String) -> Unit,
    onDeclineFriendRequest: (String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    if (uiState.notifications.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No notifications yet",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    val unread = uiState.notifications.filter { !it.isRead }
    val read = uiState.notifications.filter { it.isRead }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding),
        contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp)
    ) {
        if (unread.isNotEmpty()) {
            item {
                SectionLabel(text = "New")
            }
            items(unread, key = { it.id }) { notif ->
                NotificationItem(
                    notification = notif,
                    onAccept = onAcceptFriendRequest,
                    onDecline = onDeclineFriendRequest,
                    navController = navController,
                    isUnread = true
                )
            }
        }

        if (read.isNotEmpty()) {
            item {
                SectionLabel(
                    text = "Earlier",
                    modifier = Modifier.padding(top = if (unread.isNotEmpty()) 8.dp else 0.dp)
                )
            }
            items(read, key = { it.id }) { notif ->
                NotificationItem(
                    notification = notif,
                    onAccept = onAcceptFriendRequest,
                    onDecline = onDeclineFriendRequest,
                    navController = navController,
                    isUnread = false
                )
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 6.dp)
    )
}