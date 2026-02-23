package com.tosak.lately.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.FriendsRepository
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
class ProfileViewModel @Inject constructor(
  private val profileRepository: ProfileRepository,
  private val friendsRepository: FriendsRepository
) : ViewModel() {

  private val _isLoading = MutableStateFlow(true)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val uiState: StateFlow<ProfileUiState> = combine(
    profileRepository.profileState,
    friendsRepository.friendCount,
    _isLoading,
    _errorMessage
  ) { profile, friendCount, loading, error ->
    ProfileUiState(
      displayName = profile.displayName,
      username = profile.username,
      avatarUrl = profile.avatarUrl,
      friendCount = friendCount,
      isLoading = loading,
      errorMessage = error
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = ProfileUiState(isLoading = true))

  init {
    viewModelScope.launch {
      _isLoading.value = true

      try {
        profileRepository.loadProfile()
          .onFailure { _errorMessage.value = "Failed to load profile" }

        friendsRepository.loadFriendCount()
          .onFailure { _errorMessage.value = "Failed to load friend count" }
      } finally {
        _isLoading.value = false
      }
    }
  }

  fun deactivateAccount() {

    viewModelScope.launch {
      // TODO: authRepository.deactivateAccount()
    }
  }
}