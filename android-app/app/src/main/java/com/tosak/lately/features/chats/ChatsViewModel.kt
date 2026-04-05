package com.tosak.lately.features.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.ChatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val chatsRepository: ChatsRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    val uiState = combine(
        _query,
        chatsRepository.chatPreviews,
        _isLoading
    ) { query, chats, loading ->
        ChatsUiState(
            query = query,
            allChats = chats,
            isLoading = loading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChatsUiState()
    )

    fun onQueryChange(query: String) {
        _query.value = query
    }
}