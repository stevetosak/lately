package com.tosak.lately.core.data.repository

import com.tosak.lately.features.profile.ProfileUser
import com.tosak.lately.features.search.FriendshipStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
  private val friendsRepository: FriendsRepository
) {

  private val _user = MutableStateFlow<ProfileUser?>(null)
  val user: StateFlow<ProfileUser?> = _user.asStateFlow()

  suspend fun loadProfile(profileId: String): Result<Unit> = runCatching {
    _user.value = null

    // TODO: replace with API call — response DTO will contain both user info and friendship status
    kotlinx.coroutines.delay(800)
    _user.value = MOCK_USERS[profileId] ?: error("User not found")
  }

  suspend fun sendFriendRequest(userId: String): Result<Unit> = runCatching {
    // TODO: replace with API call
    kotlinx.coroutines.delay(300)
    _user.update { it?.copy(friendshipStatus = FriendshipStatus.REQUEST_SENT) }
  }

  suspend fun cancelFriendRequest(userId: String): Result<Unit> = runCatching {
    // TODO: replace with API call
    kotlinx.coroutines.delay(300)
    _user.update { it?.copy(friendshipStatus = FriendshipStatus.NONE) }
  }

  suspend fun removeFriend(userId: String): Result<Unit> = runCatching {
    // TODO: replace with API call
    kotlinx.coroutines.delay(300)
    _user.update { it?.copy(
      friendshipStatus = FriendshipStatus.NONE,
      friendCount = (it.friendCount - 1).coerceAtLeast(0)
    )}
    friendsRepository.removeFriend(userId)
  }

  suspend fun blockUser(userId: String): Result<Unit> = runCatching {
    // TODO: replace with API call
    kotlinx.coroutines.delay(300)
    _user.value = null
  }

  private val MOCK_USERS = mapOf(
    "1" to ProfileUser("1", "Jordan Lee", "@jordanlee", "https://randomuser.me/api/portraits/men/7.jpg", friendCount = 142, friendshipStatus = FriendshipStatus.FRIENDS),
    "2" to ProfileUser("2", "Maya Patel", "@mayapatel", "https://randomuser.me/api/portraits/women/10.jpg", friendCount = 98, friendshipStatus = FriendshipStatus.FRIENDS),
    "3" to ProfileUser("3", "Chris Novak", "@chrisnovak", "https://randomuser.me/api/portraits/men/14.jpg", friendCount = 76, friendshipStatus = FriendshipStatus.FRIENDS),
    "4" to ProfileUser("4", "Sofia Reyes", "@sofiareyes", "https://randomuser.me/api/portraits/women/2.jpg", friendCount = 210, friendshipStatus = FriendshipStatus.FRIENDS),
    "5" to ProfileUser("5", "Ethan Brooks", "@ethanbrooks", "https://randomuser.me/api/portraits/men/15.jpg", friendCount = 54, friendshipStatus = FriendshipStatus.FRIENDS),
    "6" to ProfileUser("6", "Priya Sharma", "@priyasharma", "https://randomuser.me/api/portraits/women/8.jpg", friendCount = 187, friendshipStatus = FriendshipStatus.FRIENDS),
    "7" to ProfileUser("7", "Luca Ferraro", "@lucaferraro", "https://randomuser.me/api/portraits/men/13.jpg", friendCount = 63, friendshipStatus = FriendshipStatus.FRIENDS),
    "8" to ProfileUser("8", "Nadia Wolff", "@nadiawolff", "https://randomuser.me/api/portraits/women/17.jpg", friendCount = 119, friendshipStatus = FriendshipStatus.FRIENDS),
    "9" to ProfileUser("9", "Oliver Chen", "@oliverchen", "https://randomuser.me/api/portraits/men/21.jpg", friendCount = 234, friendshipStatus = FriendshipStatus.FRIENDS),
    "10" to ProfileUser("10", "Amelia Rossi", "@ameliarossi", "https://randomuser.me/api/portraits/women/22.jpg", friendCount = 88, friendshipStatus = FriendshipStatus.FRIENDS),
    "29" to ProfileUser("29", "Alice Wang", "@alicewang", "https://randomuser.me/api/portraits/women/50.jpg", friendCount = 142, friendshipStatus = FriendshipStatus.NONE),
    "30" to ProfileUser("30", "Ben Carter", "@bencarter", "https://randomuser.me/api/portraits/men/50.jpg", friendCount = 87, friendshipStatus = FriendshipStatus.REQUEST_SENT),
    "31" to ProfileUser("31", "Carmen López", "@carmenlopez", "https://randomuser.me/api/portraits/women/51.jpg", friendCount = 210, friendshipStatus = FriendshipStatus.NONE),
    "32" to ProfileUser("32", "David Kim", "@davidkim", "https://randomuser.me/api/portraits/men/51.jpg", friendCount = 65, friendshipStatus = FriendshipStatus.REQUEST_SENT),
    "33" to ProfileUser("33", "Eva Fischer", "@evafischer", "https://randomuser.me/api/portraits/women/52.jpg", friendCount = 178, friendshipStatus = FriendshipStatus.NONE),
    "34" to ProfileUser("34", "Frank Osei", "@frankosei", "https://randomuser.me/api/portraits/men/52.jpg", friendCount = 94, friendshipStatus = FriendshipStatus.REQUEST_SENT),
    "35" to ProfileUser("35", "Grace Lin", "@gracelin", "https://randomuser.me/api/portraits/women/53.jpg", friendCount = 120, friendshipStatus = FriendshipStatus.NONE),
    "36" to ProfileUser("36", "Hassan Ali", "@hassanali", "https://randomuser.me/api/portraits/men/53.jpg", friendCount = 156, friendshipStatus = FriendshipStatus.REQUEST_SENT),
  )
}