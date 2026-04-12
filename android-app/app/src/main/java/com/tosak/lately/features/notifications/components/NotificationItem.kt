package com.tosak.lately.features.notifications.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tosak.lately.features.notifications.AppNotification
import com.tosak.lately.features.notifications.FriendRequestStatus
import com.tosak.lately.features.search.SearchUser
import com.tosak.lately.navigation.Destinations

@Composable
fun NotificationItem(
    notification: AppNotification,
    onAccept: (String) -> Unit,
    onDecline: (String) -> Unit,
    navController: NavController,
    isUnread: Boolean,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        targetValue = if (isUnread)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
        else
            MaterialTheme.colorScheme.background,
        label = "notif_bg"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(bgColor)
            .clickable(
                enabled = notification !is AppNotification.FriendRequest ||
                    (notification as AppNotification.FriendRequest).status != FriendRequestStatus.PENDING
            ) {
                when (notification) {
                    is AppNotification.FriendAccepted ->
                        navController.navigate(Destinations.Profile.route(notification.sender.id))
                    is AppNotification.StoryReply ->
                        navController.navigate(Destinations.LiveStoryViewer.route(notification.storyId))
                    is AppNotification.NearbyStory ->
                        navController.navigate(Destinations.LiveStoryViewer.route(notification.storyId))
                    else -> Unit
                }
            }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Unread dot + avatar stack
            Box {
                NotificationAvatar(
                    user = notification.sender(),
                    onClick = {
                        navController.navigate(
                            Destinations.Profile.route(notification.sender().id)
                        )
                    }
                )

                if (isUnread) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .align(Alignment.TopStart)
                            .offset(x = (-2).dp, y = (-2).dp)
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = notificationText(notification),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = notification.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )

                // Broadcast message body
                if (notification is AppNotification.Broadcast) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = notification.message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Friend request actions
        if (notification is AppNotification.FriendRequest &&
            notification.status == FriendRequestStatus.PENDING
        ) {
            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 52.dp) // align under text, past avatar
            ) {
                Button(
                    onClick = { onAccept(notification.id) },
                    modifier = Modifier.height(34.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    Text("Accept", style = MaterialTheme.typography.labelMedium)
                }
                OutlinedButton(
                    onClick = { onDecline(notification.id) },
                    modifier = Modifier.height(34.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    Text("Decline", style = MaterialTheme.typography.labelMedium)
                }
            }
        }

        // Friend request resolved state
        if (notification is AppNotification.FriendRequest &&
            notification.status != FriendRequestStatus.PENDING
        ) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = if (notification.status == FriendRequestStatus.ACCEPTED) "✓ Accepted" else "✗ Declined",
                style = MaterialTheme.typography.labelSmall,
                color = if (notification.status == FriendRequestStatus.ACCEPTED)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 52.dp)
            )
        }
    }
}

@Composable
private fun NotificationAvatar(
    user: SearchUser,
    onClick: () -> Unit
) {
    AsyncImage(
        model = user.avatarUrl,
        contentDescription = user.displayName,
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onClick)
    )
}


@Composable
private fun notificationText(notification: AppNotification) = buildAnnotatedString {
    val bold = SpanStyle(fontWeight = FontWeight.SemiBold)
    when (notification) {
        is AppNotification.FriendRequest -> {
            withStyle(bold) { append(notification.sender.username) }
            append(" wants to be your friend")
        }
        is AppNotification.FriendAccepted -> {
            withStyle(bold) { append(notification.sender.username) }
            append(" accepted your friend request")
        }
        is AppNotification.StoryReply -> {
            withStyle(bold) { append(notification.sender.username) }
            append(" replied to your story: ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                append("\"${notification.replyText}\"")
            }
        }
        is AppNotification.NearbyStory -> {
            withStyle(bold) { append(notification.sender.username) }
            append(" posted a story near you")
            append(" · ${notification.locationLabel}")
        }
        is AppNotification.Broadcast -> {
            withStyle(bold) { append(notification.sender.username) }
            append(" sent a broadcast")
        }
    }
}

// Helper to extract sender from any notification type
private fun AppNotification.sender(): SearchUser = when (this) {
    is AppNotification.FriendRequest -> sender
    is AppNotification.FriendAccepted -> sender
    is AppNotification.StoryReply -> sender
    is AppNotification.NearbyStory -> sender
    is AppNotification.Broadcast -> sender
}