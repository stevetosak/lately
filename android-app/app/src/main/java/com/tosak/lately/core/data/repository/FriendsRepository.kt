package com.tosak.lately.core.data.repository

import com.tosak.lately.features.friends.Friend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendsRepository @Inject constructor() {

  private val _friends = MutableStateFlow<List<Friend>>(emptyList())
  val friends: StateFlow<List<Friend>> = _friends.asStateFlow()

  private val _friendCount = MutableStateFlow(0)
  val friendCount: StateFlow<Int> = _friendCount.asStateFlow()

  suspend fun loadFriends(): Result<Unit> = runCatching {

    if (_friends.value.isNotEmpty()) return@runCatching

    // TODO: replace with API/DB call
    kotlinx.coroutines.delay(2000)
    _friends.value = listOf(
      Friend("1", "Jordan Lee", "@jordanlee", avatarUrl = "https://randomuser.me/api/portraits/men/7.jpg"),
      Friend("2", "Maya Patel", "@mayapatel", avatarUrl = "https://randomuser.me/api/portraits/women/10.jpg"),
      Friend("3", "Chris Novak", "@chrisnovak", avatarUrl = "https://randomuser.me/api/portraits/men/14.jpg"),
      Friend("4", "Sofia Reyes", "@sofiareyes", avatarUrl = "https://randomuser.me/api/portraits/women/2.jpg"),
      Friend("5", "Ethan Brooks", "@ethanbrooks", avatarUrl = "https://randomuser.me/api/portraits/men/15.jpg"),
      Friend("6", "Priya Sharma", "@priyasharma", avatarUrl = "https://randomuser.me/api/portraits/women/8.jpg"),
      Friend("7", "Luca Ferraro", "@lucaferraro", avatarUrl = "https://randomuser.me/api/portraits/men/13.jpg"),
      Friend("8", "Nadia Wolff", "@nadiawolff", avatarUrl = "https://randomuser.me/api/portraits/women/17.jpg"),
      Friend("9", "Oliver Chen", "@oliverchen", avatarUrl = "https://randomuser.me/api/portraits/men/21.jpg"),
      Friend("10", "Amelia Rossi", "@ameliarossi", avatarUrl = "https://randomuser.me/api/portraits/women/22.jpg")
    )
  }

  suspend fun loadFriendCount(): Result<Unit> = runCatching {
    // TODO: replace with API/DB call that fetches count only
    _friendCount.value = 10
  }

  fun removeFriend(id: String) {
    // TODO: replace with API/DB call
    _friends.update { friends -> friends.filterNot { it.id == id } }
    _friendCount.value = _friends.value.size
  }

  fun blockFriend(id: String) {
    // TODO: replace with API/DB call
    _friends.update { friends -> friends.filterNot { it.id == id } }
    _friendCount.value = _friends.value.size
  }
}