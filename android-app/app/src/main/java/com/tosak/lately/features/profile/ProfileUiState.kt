package com.tosak.lately.features.profile

data class ProfileUiState(
  val displayName: String = "",
  val username: String = "",
  val avatarUrl: String? = null,
  val friendCount: Int = 0,
  val isLoading: Boolean = true,
  val errorMessage: String? = null
)