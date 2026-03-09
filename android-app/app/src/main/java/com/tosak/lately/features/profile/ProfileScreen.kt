package com.tosak.lately.features.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.core.ui.components.bars.AppTopBar
import com.tosak.lately.core.ui.components.feedback.ScreenLoading
import com.tosak.lately.features.profile.components.ProfileAchievements
import com.tosak.lately.features.profile.components.ProfileActions
import com.tosak.lately.features.profile.components.ProfileCard
import com.tosak.lately.features.profile.components.UnfriendDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
  navController: NavController
) {
  val viewModel: ProfileViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  var showUnfriendDialog by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      AppTopBar(
        title = "",
        navController = navController
      )
    },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    if (uiState.isLoading || uiState.user == null) {
      ScreenLoading()
    } else {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        ProfileCard(user = uiState.user!!)
        ProfileActions(
          friendshipStatus = uiState.user!!.friendshipStatus,
          onSendRequest = viewModel::sendFriendRequest,
          onCancelRequest = viewModel::cancelFriendRequest,
          onUnfriendClick = { showUnfriendDialog = true },
          onMessageClick = { /* TODO: navigate to messages */ },
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
        )
        ProfileAchievements()
      }
    }
  }

  if (showUnfriendDialog) {
    UnfriendDialog(
      displayName = uiState.user?.displayName.orEmpty(),
      onConfirm = {
        viewModel.removeFriend()
        showUnfriendDialog = false
      },
      onDismiss = { showUnfriendDialog = false }
    )
  }
}