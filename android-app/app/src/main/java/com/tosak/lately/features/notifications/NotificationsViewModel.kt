package com.tosak.lately.features.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: NotificationsRepository
) : ViewModel() {

    val uiState = repository.notifications
        .map { NotificationsUiState(notifications = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotificationsUiState(isLoading = true)
        )

    fun acceptFriendRequest(notificationId: String) {
        viewModelScope.launch {
            repository.acceptFriendRequest(notificationId)
        }
    }

    fun declineFriendRequest(notificationId: String) {
        viewModelScope.launch {
            repository.declineFriendRequest(notificationId)
        }
    }

    fun markAllAsRead() {
        viewModelScope.launch {
            repository.markAllAsRead()
        }
    }
}