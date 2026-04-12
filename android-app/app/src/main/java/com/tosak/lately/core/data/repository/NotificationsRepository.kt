package com.tosak.lately.core.data.repository

import com.tosak.lately.features.notifications.AppNotification
import com.tosak.lately.features.notifications.FriendRequestStatus
import com.tosak.lately.features.search.FriendshipStatus
import com.tosak.lately.features.search.SearchUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsRepository @Inject constructor() {

    private val _notifications = MutableStateFlow<List<AppNotification>>(MOCK_NOTIFICATIONS)
    val notifications: StateFlow<List<AppNotification>> = _notifications.asStateFlow()

    suspend fun acceptFriendRequest(notificationId: String) {
        // TODO: replace with API call
        delay(300)
        _notifications.update { list ->
            list.map { notif ->
                if (notif is AppNotification.FriendRequest && notif.id == notificationId)
                    notif.copy(status = FriendRequestStatus.ACCEPTED)
                else notif
            }
        }
    }

    suspend fun declineFriendRequest(notificationId: String) {
        // TODO: replace with API call
        delay(300)
        _notifications.update { list ->
            list.map { notif ->
                if (notif is AppNotification.FriendRequest && notif.id == notificationId)
                    notif.copy(status = FriendRequestStatus.DECLINED)
                else notif
            }
        }
    }

    suspend fun markAllAsRead() {
        // TODO: replace with API call
        delay(200)
        _notifications.update { list ->
            list.map { notif ->
                when (notif) {
                    is AppNotification.FriendRequest -> notif.copy(isRead = true)
                    is AppNotification.FriendAccepted -> notif.copy(isRead = true)
                    is AppNotification.StoryReply -> notif.copy(isRead = true)
                    is AppNotification.NearbyStory -> notif.copy(isRead = true)
                    is AppNotification.Broadcast -> notif.copy(isRead = true)
                }
            }
        }
    }

    companion object {
        val MOCK_NOTIFICATIONS = listOf(
            AppNotification.FriendRequest(
                id = "n1",
                sender = SearchRepository.MOCK_USERS[10],
                timestamp = "2m ago",
                isRead = false
            ),
            AppNotification.FriendRequest(
                id = "n2",
                sender = SearchRepository.MOCK_USERS[11],
                timestamp = "15m ago",
                isRead = false
            ),
            AppNotification.FriendAccepted(
                id = "n3",
                sender = SearchRepository.MOCK_USERS[4],
                timestamp = "1h ago",
                isRead = false
            ),
            AppNotification.StoryReply(
                id = "n4",
                sender = SearchRepository.MOCK_USERS[3],
                replyText = "Wow, what a place! 😍",
                storyId = "story_42",
                timestamp = "2h ago",
                isRead = false
            ),
            AppNotification.Broadcast(
                id = "n5",
                sender = SearchRepository.MOCK_USERS[5],
                message = "Hey everyone! We're hosting a meetup at the central park this Saturday at 4pm. Come join us! 🎉",
                timestamp = "3h ago",
                isRead = true
            ),
            AppNotification.NearbyStory(
                id = "n6",
                sender = SearchRepository.MOCK_USERS[13],
                storyId = "story_002",
                locationLabel = "Old Bazaar",
                timestamp = "4h ago",
                isRead = true
            ),
            AppNotification.FriendRequest(
                id = "n7",
                sender = SearchRepository.MOCK_USERS[5],
                timestamp = "Yesterday",
                isRead = true,
                status = FriendRequestStatus.ACCEPTED
            ),
            AppNotification.StoryReply(
                id = "n8",
                sender = SearchRepository.MOCK_USERS[0],
                replyText = "This is near my place!",
                storyId = "story_09",
                timestamp = "Yesterday",
                isRead = true
            ),
            AppNotification.Broadcast(
                id = "n9",
                sender = SearchRepository.MOCK_USERS[3],
                message = "Just a heads up — the old train station area has some really cool street art right now if anyone's around 🎨",
                timestamp = "2 days ago",
                isRead = true
            ),
        )
    }
}