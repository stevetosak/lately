package com.tosak.lately.features.search

data class SearchUiState(
  val query: String = "",
  val results: List<SearchUser> = emptyList(),
  val searchHistory: List<String> = emptyList(),
  val isLoading: Boolean = false,
  val errorMessage: String? = null
) {
  /** True when query is blank and no results are shown — show history instead. */
  val showHistory: Boolean get() = query.isBlank()
}

data class SearchUser(
  val id: String,
  val displayName: String,
  val username: String,
  val avatarUrl: String? = null,
  val friendshipStatus: FriendshipStatus = FriendshipStatus.NONE
)

enum class FriendshipStatus {
  /** No relationship. */
  NONE,

  /** Current user has sent a friend request, awaiting acceptance. */
  REQUEST_SENT,

  /** Already friends. */
  FRIENDS
}