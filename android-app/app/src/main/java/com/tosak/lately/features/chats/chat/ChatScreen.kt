package com.tosak.lately.features.chats.chat

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.features.chats.chat.components.ChatContent
import com.tosak.lately.features.chats.chat.components.ChatTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController) {
    val viewModel: ChatViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ChatTopBar(
                user = uiState.user,
                navController = navController
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        ChatContent(
            innerPadding = innerPadding,
            uiState = uiState,
            onInputChange = viewModel::onInputChange,
            onSend = viewModel::sendMessage
        )
    }
}