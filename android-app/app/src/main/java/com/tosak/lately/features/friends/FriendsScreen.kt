package com.tosak.lately.features.friends

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.core.ui.components.AppTopBar
import com.tosak.lately.core.ui.components.ScreenLoading
import com.tosak.lately.features.friends.components.BlockFriendDialog
import com.tosak.lately.features.friends.components.FriendsList
import com.tosak.lately.features.friends.components.RemoveFriendDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(
  navController: NavController,
) {
  val viewModel: FriendsViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  var searchQuery by remember { mutableStateOf("") }

  val filteredFriends by remember {
    derivedStateOf {
      if (searchQuery.isBlank()) {
        uiState.friends
      } else {
        uiState.friends.filter {
          it.displayName.contains(searchQuery, ignoreCase = true) ||
            it.username.contains(searchQuery, ignoreCase = true)
        }
      }
    }
  }

  val hasNoFriends = uiState.friends.isEmpty()

  var pendingRemovalFriend by remember { mutableStateOf<Friend?>(null) }
  var pendingBlockFriend by remember { mutableStateOf<Friend?>(null) }


  Scaffold(
    topBar = {
      AppTopBar(
        title = "${uiState.friendCount} Friends",
        navController = navController
      )
    },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    if (uiState.isLoading) {
      ScreenLoading()
    } else {
      FriendsList(
        innerPadding = innerPadding,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        filteredFriends = filteredFriends,
        hasNoFriends = hasNoFriends,
        onMessageClick = { /* TODO: navigate to Messages with friend.id */ },
        onRemoveClick = { pendingRemovalFriend = it },
        onBlockClick = { pendingBlockFriend = it }
      )
    }
  }

  pendingRemovalFriend?.let { friend ->
    RemoveFriendDialog(
      displayName = friend.displayName,
      onConfirm = {
        viewModel.removeFriend(friend.id)
        pendingRemovalFriend = null
      },
      onDismiss = { pendingRemovalFriend = null }
    )
  }

  pendingBlockFriend?.let { friend ->
    BlockFriendDialog(
      displayName = friend.displayName,
      onConfirm = {
        viewModel.blockFriend(friend.id)
        pendingBlockFriend = null
      },
      onDismiss = { pendingBlockFriend = null }
    )
  }
}
