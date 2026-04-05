package com.tosak.lately.features.chats.chat

import com.tosak.lately.features.search.SearchUser

data class ChatMessage(
    val id: String,
    val text: String,
    val isFromMe: Boolean,
    val timestamp: String
)

data class ChatUiState(
    val user: SearchUser? = null,
    val messages: List<ChatMessage> = emptyList(),
    val inputText: String = "",
    val isTyping: Boolean = false
)