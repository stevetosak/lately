package com.tosak.lately.core.data.repository

import com.tosak.lately.features.search.SearchUser
import com.tosak.lately.features.search.FriendshipStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor() {

  private val _results = MutableStateFlow<List<SearchUser>>(emptyList())
  val results: StateFlow<List<SearchUser>> = _results.asStateFlow()

  private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
  val searchHistory: StateFlow<List<String>> = _searchHistory.asStateFlow()

  /**
   * Performs a backend search for users matching [query].
   * Returns a Result indicating success or failure.
   * Filtering happens server-side; we only receive matched results.
   */
  suspend fun searchUsers(query: String): Result<Unit> = runCatching {

    if (query.isBlank()) {
      _results.value = emptyList()
      return@runCatching
    }

    // TODO: replace with API call
    //   val response = api.searchUsers(query)
    //   _results.value = response.map { it.toSearchUser() }
    kotlinx.coroutines.delay(400)
    _results.value = MOCK_USERS.filter {
      it.displayName.contains(query, ignoreCase = true) ||
        it.username.contains(query, ignoreCase = true)
    }

    addToHistory(query)
  }

  fun clearResults() {
    _results.value = emptyList()
  }

  private fun addToHistory(query: String) {

    val trimmedQuery = query.trim()
    if (trimmedQuery.isBlank()) return

    _searchHistory.update { history ->
      val updatedHistory = history.filterNot { it.equals(trimmedQuery, ignoreCase = true) }
      listOf(trimmedQuery) + updatedHistory
    }
  }

  suspend fun removeFromHistory(query: String) {
    // TODO: replace with API call
    kotlinx.coroutines.delay(300)
    _searchHistory.update { it.filterNot { item -> item == query } }
  }

  suspend fun clearHistory() {
    // TODO: replace with API call
    kotlinx.coroutines.delay(300)
    _searchHistory.value = emptyList()
  }

  companion object {
    val MOCK_USERS = listOf(
      SearchUser("1", "Jordan Lee", "@jordanlee", "https://randomuser.me/api/portraits/men/7.jpg", FriendshipStatus.FRIENDS),
      SearchUser("2", "Maya Patel", "@mayapatel", "https://randomuser.me/api/portraits/women/10.jpg", FriendshipStatus.FRIENDS),
      SearchUser("3", "Chris Novak", "@chrisnovak", "https://randomuser.me/api/portraits/men/14.jpg", FriendshipStatus.FRIENDS),
      SearchUser("4", "Sofia Reyes", "@sofiareyes", "https://randomuser.me/api/portraits/women/2.jpg", FriendshipStatus.FRIENDS),
      SearchUser("5", "Ethan Brooks", "@ethanbrooks", "https://randomuser.me/api/portraits/men/15.jpg", FriendshipStatus.FRIENDS),
      SearchUser("6", "Priya Sharma", "@priyasharma", "https://randomuser.me/api/portraits/women/8.jpg", FriendshipStatus.FRIENDS),
      SearchUser("7", "Luca Ferraro", "@lucaferraro", "https://randomuser.me/api/portraits/men/13.jpg", FriendshipStatus.FRIENDS),
      SearchUser("8", "Nadia Wolff", "@nadiawolff", "https://randomuser.me/api/portraits/women/17.jpg", FriendshipStatus.FRIENDS),
      SearchUser("9", "Oliver Chen", "@oliverchen", "https://randomuser.me/api/portraits/men/21.jpg", FriendshipStatus.FRIENDS),
      SearchUser("10", "Amelia Rossi", "@ameliarossi", "https://randomuser.me/api/portraits/women/22.jpg", FriendshipStatus.FRIENDS),
      SearchUser("29", "Alice Wang", "@alicewang", "https://randomuser.me/api/portraits/women/50.jpg", FriendshipStatus.NONE),
      SearchUser("30", "Ben Carter", "@bencarter", "https://randomuser.me/api/portraits/men/50.jpg", FriendshipStatus.REQUEST_SENT),
      SearchUser("31", "Carmen López", "@carmenlopez", "https://randomuser.me/api/portraits/women/51.jpg", FriendshipStatus.NONE),
      SearchUser("32", "David Kim", "@davidkim", "https://randomuser.me/api/portraits/men/51.jpg", FriendshipStatus.REQUEST_SENT),
      SearchUser("33", "Eva Fischer", "@evafischer", "https://randomuser.me/api/portraits/women/52.jpg", FriendshipStatus.NONE),
      SearchUser("34", "Frank Osei", "@frankosei", "https://randomuser.me/api/portraits/men/52.jpg", FriendshipStatus.REQUEST_SENT),
      SearchUser("35", "Grace Lin", "@gracelin", "https://randomuser.me/api/portraits/women/53.jpg", FriendshipStatus.NONE),
      SearchUser("36", "Hassan Ali", "@hassanali", "https://randomuser.me/api/portraits/men/53.jpg", FriendshipStatus.REQUEST_SENT),
    )
  }
}