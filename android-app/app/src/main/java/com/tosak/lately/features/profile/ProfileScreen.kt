package com.tosak.lately.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.navigation.Destinations
import com.tosak.lately.components.DestructiveButton
import com.tosak.lately.components.ScreenLoading
import com.tosak.lately.features.profile.components.DeactivateAccountDialog
import com.tosak.lately.features.profile.components.ProfileActionsCard
import com.tosak.lately.features.profile.components.ProfileHeader

@Composable
fun ProfileScreen(
  navController: NavController
) {
  val viewModel: ProfileViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  var showDeactivateDialog by remember { mutableStateOf(false) }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    if (uiState.isLoading) {
      ScreenLoading()
    } else {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 24.dp)
          .padding(top = 64.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        ProfileHeader(
          uiState = uiState,
          onFriendsClick = { navController.navigate(Destinations.Friends.route) }
        )
        Spacer(Modifier.height(36.dp))
        ProfileActionsCard(
          onEditProfile = { navController.navigate(Destinations.EditProfile.route) },
          onArchivedStories = { navController.navigate(Destinations.ArchivedStories.route) },
        )
        Spacer(Modifier.height(40.dp))
        DestructiveButton(
          label = "Deactivate account",
          onClick = { showDeactivateDialog = true }
        )
        Spacer(Modifier.height(10.dp))
        DestructiveButton(
          label = "Log out",
          onClick = {  }
        )
      }
    }
  }

  if (showDeactivateDialog) {
    DeactivateAccountDialog(
      onConfirm = {
        showDeactivateDialog = false
        viewModel.deactivateAccount()
      },
      onDismiss = { showDeactivateDialog = false }
    )
  }
}
