package com.tosak.lately.features.profile

import com.tosak.lately.features.search.FriendshipStatus

data class ProfileUiState(
  val user: ProfileUser? = null,
  val isLoading: Boolean = true,
  val errorMessage: String? = null
)

data class ProfileUser(
  val id: String,
  val displayName: String,
  val username: String,
  val avatarUrl: String? = null,
  val friendCount: Int = 0,
  val friendshipStatus: FriendshipStatus = FriendshipStatus.NONE
)