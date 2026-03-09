package com.tosak.lately.features.myprofile

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
import com.tosak.lately.core.ui.components.buttons.DestructiveButton
import com.tosak.lately.core.ui.components.feedback.ScreenLoading
import com.tosak.lately.features.myprofile.components.DeactivateAccountDialog
import com.tosak.lately.features.myprofile.components.MyProfileSettings
import com.tosak.lately.features.myprofile.components.MyProfileCard

@Composable
fun MyProfileScreen(
  navController: NavController
) {
  val viewModel: MyProfileViewModel = hiltViewModel()
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
        MyProfileCard(
          uiState = uiState,
          onFriendsClick = { navController.navigate(Destinations.Friends.route) }
        )
        Spacer(Modifier.height(36.dp))
        MyProfileSettings(
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
