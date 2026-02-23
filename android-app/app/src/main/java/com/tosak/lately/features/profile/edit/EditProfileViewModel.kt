package com.tosak.lately.features.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
  private val repository: ProfileRepository
) : ViewModel() {

  private val _isLoading = MutableStateFlow(false)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val uiState: StateFlow<EditProfileUiState> = combine(
    repository.profileState,
    _isLoading,
    _errorMessage
  ) { profile, loading, error ->
    profile.copy(
      isLoading = loading,
      errorMessage = error
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = repository.profileState.value
  )

  fun updateDisplayName(name: String) = repository.updateDisplayName(name)
  fun updateUsername(username: String) = repository.updateUsername(username)
  fun updateBio(bio: String) = repository.updateBio(bio)
  fun updatePhone(phone: String) = repository.updatePhone(phone)
  fun updateAvatar(url: String) = repository.updateAvatar(url)

  fun saveProfile() {

    viewModelScope.launch {
      _isLoading.value = true
      _errorMessage.value = null

      repository.saveProfile()
        .onFailure { _errorMessage.value = "Failed to save profile" }

      _isLoading.value = false
    }
  }
}