package com.tosak.lately.core.data.repository

import com.tosak.lately.features.profile.edit.EditProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor() {

  private val _profileState = MutableStateFlow(EditProfileUiState())
  val profileState: StateFlow<EditProfileUiState> = _profileState.asStateFlow()

  suspend fun loadProfile(): Result<Unit> = runCatching {

    // TODO: replace with API/DB call
    _profileState.value = EditProfileUiState(
      displayName = "Alex Rivera",
      username = "@alexrivera",
      avatarUrl = "https://randomuser.me/api/portraits/men/24.jpg"
    )
  }

  fun updateDisplayName(name: String) = _profileState.update { it.copy(displayName = name) }
  fun updateUsername(username: String) = _profileState.update { it.copy(username = username) }
  fun updatePhone(phone: String) = _profileState.update { it.copy(phone = phone) }
  fun updateBio(bio: String) = _profileState.update { it.copy(bio = bio) }
  fun updateAvatar(url: String) = _profileState.update { it.copy(avatarUrl = url) }

  suspend fun saveProfile(): Result<Unit> = runCatching {

    // TODO: save to API/DB
    kotlinx.coroutines.delay(500)
  }
}