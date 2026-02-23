package com.tosak.lately.features.friends

data class FriendsUiState(
  val friends: List<Friend>,
  val friendCount: Int,
  val isLoading: Boolean,
  val errorMessage: String? = null
)

data class Friend(
  val id: String,
  val displayName: String,
  val username: String,
  val avatarUrl: String? = null
)

