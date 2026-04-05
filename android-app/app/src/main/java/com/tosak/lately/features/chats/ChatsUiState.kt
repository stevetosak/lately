package com.tosak.lately.features.chats

import com.tosak.lately.features.search.SearchUser

data class ChatsUiState(
    val query: String = "",
    val allChats: List<ChatPreview> = emptyList(),
    val isLoading: Boolean = false
) {
    val filteredChats: List<ChatPreview>
        get() = if (query.isBlank()) allChats
        else allChats.filter {
            it.user.displayName.contains(query, ignoreCase = true) ||
                it.user.username.contains(query, ignoreCase = true)
        }
}

data class ChatPreview(
    val user: SearchUser,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadCount: Int = 0
)
