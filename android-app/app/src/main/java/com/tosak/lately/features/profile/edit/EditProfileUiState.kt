package com.tosak.lately.features.profile.edit

data class EditProfileUiState(
  val displayName: String = "",
  val username: String = "",
  val bio: String = "",
  val phone: String = "",
  val avatarUrl: String? = null,
  val isLoading: Boolean = false,
  val errorMessage: String? = null
)