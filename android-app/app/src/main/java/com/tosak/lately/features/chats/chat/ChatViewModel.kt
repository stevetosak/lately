package com.tosak.lately.features.chats.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.ChatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatsRepository: ChatsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: String = checkNotNull(savedStateHandle["userId"])

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    private val _inputText = MutableStateFlow("")
    private val _isTyping = MutableStateFlow(false)

    val uiState = combine(
        _messages,
        _inputText,
        _isTyping
    ) { messages, input, typing ->
        ChatUiState(
            user = chatsRepository.getUserById(userId),
            messages = messages,
            inputText = input,
            isTyping = typing
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChatUiState(user = chatsRepository.getUserById(userId))
    )

    init {
        _messages.value = chatsRepository.getMessagesForUser(userId)
    }

    fun onInputChange(text: String) {
        _inputText.value = text
    }

    fun sendMessage() {
        val text = _inputText.value.trim()
        if (text.isBlank()) return

        val now = nowTime()
        val message = ChatMessage(
            id = UUID.randomUUID().toString(),
            text = text,
            isFromMe = true,
            timestamp = now
        )
        _messages.value = _messages.value + message
        _inputText.value = ""

        // TODO: remove this
        viewModelScope.launch {
            delay(800)
            _isTyping.value = true

            val typingDuration = (1200L..2800L).random()
            delay(typingDuration)
            _isTyping.value = false

            val reply = chatsRepository.generateReply(text)
            val replyMessage = ChatMessage(
                id = UUID.randomUUID().toString(),
                text = reply,
                isFromMe = false,
                timestamp = nowTime()
            )
            _messages.value = _messages.value + replyMessage
        }
    }

    private fun nowTime(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }
}