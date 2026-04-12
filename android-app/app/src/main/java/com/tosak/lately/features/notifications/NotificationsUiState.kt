package com.tosak.lately.features.notifications

import com.tosak.lately.features.search.SearchUser

data class NotificationsUiState(
    val notifications: List<AppNotification> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed class AppNotification {
    abstract val id: String
    abstract val timestamp: String
    abstract val isRead: Boolean

    data class FriendRequest(
        override val id: String,
        override val timestamp: String,
        override val isRead: Boolean = false,
        val sender: SearchUser,
        val status: FriendRequestStatus = FriendRequestStatus.PENDING
    ) : AppNotification()

    data class FriendAccepted(
        override val id: String,
        override val timestamp: String,
        override val isRead: Boolean = false,
        val sender: SearchUser
    ) : AppNotification()

    data class StoryReply(
        override val id: String,
        override val timestamp: String,
        override val isRead: Boolean = false,
        val sender: SearchUser,
        val replyText: String,
        val storyId: String
    ) : AppNotification()

    data class NearbyStory(
        override val id: String,
        override val timestamp: String,
        override val isRead: Boolean = false,
        val sender: SearchUser,
        val storyId: String,
        val locationLabel: String
    ) : AppNotification()

    data class Broadcast(
        override val id: String,
        override val timestamp: String,
        override val isRead: Boolean = false,
        val sender: SearchUser,
        val message: String
    ) : AppNotification()
}

enum class FriendRequestStatus { PENDING, ACCEPTED, DECLINED }