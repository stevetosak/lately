package com.tosak.lately.features.chats

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.core.ui.components.bars.AppTopBar
import com.tosak.lately.features.chats.components.ChatsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(navController: NavController) {
    val viewModel: ChatsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Messages",
                navController = navController
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        ChatsContent(
            innerPadding = innerPadding,
            uiState = uiState,
            onQueryChange = viewModel::onQueryChange,
            modifier = Modifier.fillMaxSize(),
            navController = navController
        )
    }
}