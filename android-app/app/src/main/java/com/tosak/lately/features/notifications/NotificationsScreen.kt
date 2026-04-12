package com.tosak.lately.features.notifications

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.core.ui.components.bars.AppTopBar
import com.tosak.lately.features.notifications.components.NotificationsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController) {
  val viewModel: NotificationsViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  val hasUnread = uiState.notifications.any { !it.isRead }

  Scaffold(
    topBar = {
      AppTopBar(
        title = "Notifications",
        navController = navController,
        actions = {
          if (hasUnread) {
            TextButton(onClick = viewModel::markAllAsRead) {
              Text(
                text = "Mark all read",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
              )
            }
          }
        }
      )
    },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    NotificationsContent(
      innerPadding = innerPadding,
      uiState = uiState,
      onAcceptFriendRequest = viewModel::acceptFriendRequest,
      onDeclineFriendRequest = viewModel::declineFriendRequest,
      modifier = Modifier.fillMaxSize(),
      navController = navController
    )
  }
}