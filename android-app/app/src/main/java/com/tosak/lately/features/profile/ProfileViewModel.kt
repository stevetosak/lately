package com.tosak.lately.features.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val profileRepository: ProfileRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val profileId: String = checkNotNull(savedStateHandle["profileId"])

  private val _isLoading = MutableStateFlow(false)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val uiState = combine(
    profileRepository.user,
    _isLoading,
    _errorMessage
  ) { user, loading, error ->
    ProfileUiState(
      user = user,
      isLoading = loading,
      errorMessage = error
    )
  }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = ProfileUiState()
    )

  init {
    viewModelScope.launch {
      _isLoading.value = true

      try {
        profileRepository.loadProfile(profileId)
          .onFailure { _errorMessage.value = "Failed to load profile" }

      } finally {
        _isLoading.value = false
      }
    }
  }

  fun sendFriendRequest() {
    viewModelScope.launch {
      profileRepository.sendFriendRequest(profileId)
    }
  }

  fun cancelFriendRequest() {
    viewModelScope.launch {
      profileRepository.cancelFriendRequest(profileId)
    }
  }

  fun removeFriend() {
    viewModelScope.launch {
      profileRepository.removeFriend(profileId)
    }
  }

  fun blockUser() {
    viewModelScope.launch {
      profileRepository.blockUser(profileId)
    }
  }
}