package com.tosak.lately.features.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.FriendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
  private val friendsRepository: FriendsRepository
) : ViewModel() {

  private val _isLoading = MutableStateFlow(true)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val uiState: StateFlow<FriendsUiState> = combine(
    friendsRepository.friends,
    friendsRepository.friendCount,
    _isLoading,
    _errorMessage
  ) { friends, friendCount, loading, error ->
    FriendsUiState(
      friends = friends,
      friendCount = friendCount,
      isLoading = loading,
      errorMessage = error
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = FriendsUiState(
      friends = emptyList(),
      friendCount = friendsRepository.friendCount.value,
      isLoading = true
    )
  )

  init {
    viewModelScope.launch {
      _isLoading.value = true

      friendsRepository.loadFriends()
        .onFailure { _errorMessage.value = "Failed to load friends" }

      _isLoading.value = false
    }
  }

  fun removeFriend(friendId: String) {
    viewModelScope.launch {
      friendsRepository.removeFriend(friendId)
    }
  }

  fun blockFriend(friendId: String) {
    viewModelScope.launch {
      friendsRepository.blockFriend(friendId)
    }
  }
}