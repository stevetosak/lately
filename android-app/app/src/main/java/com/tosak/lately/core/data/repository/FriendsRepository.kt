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
      Friend("10", "Amelia Rossi", "@ameliarossi", avatarUrl = "https://randomuser.me/api/portraits/women/22.jpg"),
      Friend("11", "Mateo Silva", "@mateosilva", avatarUrl = "https://randomuser.me/api/portraits/men/23.jpg"),
      Friend("12", "Isabella Cruz", "@isabellacruz", avatarUrl = "https://randomuser.me/api/portraits/women/24.jpg"),
      Friend("13", "Noah Kim", "@noahkim", avatarUrl = "https://randomuser.me/api/portraits/men/25.jpg"),
      Friend("14", "Layla Ahmed", "@laylaahmed", avatarUrl = "https://randomuser.me/api/portraits/women/26.jpg"),
      Friend("15", "Sebastian Müller", "@sebastianmuller", avatarUrl = "https://randomuser.me/api/portraits/men/27.jpg"),
      Friend("16", "Ava Thompson", "@avathompson", avatarUrl = "https://randomuser.me/api/portraits/women/28.jpg"),
      Friend("17", "Hiro Tanaka", "@hiro", avatarUrl = "https://randomuser.me/api/portraits/men/29.jpg"),
      Friend("18", "Chloe Dubois", "@chloedubois", avatarUrl = "https://randomuser.me/api/portraits/women/30.jpg"),
      Friend("19", "Diego Morales", "@diegomorales", avatarUrl = "https://randomuser.me/api/portraits/men/31.jpg"),
      Friend("20", "Emma Johansson", "@emmajohansson", avatarUrl = "https://randomuser.me/api/portraits/women/32.jpg"),
      Friend("21", "Arjun Mehta", "@arjunmehta", avatarUrl = "https://randomuser.me/api/portraits/men/33.jpg"),
      Friend("22", "Sienna Brown", "@siennabrown", avatarUrl = "https://randomuser.me/api/portraits/women/34.jpg"),
      Friend("23", "Kai Nakamura", "@kainakamura", avatarUrl = "https://randomuser.me/api/portraits/men/35.jpg"),
      Friend("24", "Valentina Costa", "@valentinacosta", avatarUrl = "https://randomuser.me/api/portraits/women/36.jpg"),
      Friend("25", "Jonas Berg", "@jonasberg", avatarUrl = "https://randomuser.me/api/portraits/men/37.jpg"),
      Friend("26", "Freya Olsen", "@freyaolsen", avatarUrl = "https://randomuser.me/api/portraits/women/38.jpg"),
      Friend("27", "Marcus Hall", "@marcushall", avatarUrl = "https://randomuser.me/api/portraits/men/39.jpg"),
      Friend("28", "Elena Petrova", "@elenapetrova", avatarUrl = "https://randomuser.me/api/portraits/women/40.jpg")
    )
  }

  suspend fun loadFriendCount(): Result<Unit> = runCatching {

    // TODO: replace with API/DB call that fetches count only
    _friendCount.value = 28
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