package com.tosak.lately.features.friends.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tosak.lately.features.friends.Friend


@Composable
fun FriendsList(
  innerPadding: PaddingValues,
  searchQuery: String,
  onSearchQueryChange: (String) -> Unit,
  filteredFriends: List<Friend>,
  hasNoFriends: Boolean,
  onMessageClick: (Friend) -> Unit,
  onRemoveClick: (Friend) -> Unit,
  onBlockClick: (Friend) -> Unit
) {

  val listState = rememberLazyListState()

  // TODO: consider batch loading
  LazyColumn(
    state = listState,
    modifier = Modifier.padding(innerPadding),
    verticalArrangement = Arrangement.spacedBy(4.dp)
  ) {
    item {
      FriendsSearchBar(
        searchText = searchQuery,
        onSearchTextChange = onSearchQueryChange,
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
      )
    }
    if (filteredFriends.isEmpty()) {
      item {
        FriendsEmpty(
          noFriendsAtAll = hasNoFriends,
          modifier = Modifier
            .fillParentMaxHeight(0.6f)
            .fillMaxWidth()
        )
      }
    } else {
      items(filteredFriends, key = { it.id }) { friend ->
        FriendCard(
          friend = friend,
          onMessageClick = { onMessageClick(friend) },
          onRemoveClick = { onRemoveClick(friend) },
          onBlockClick = { onBlockClick(friend) }
        )
      }
    }
  }
}

@Composable
private fun FriendsEmpty(
  noFriendsAtAll: Boolean,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier, contentAlignment = Alignment.Center) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text = if (noFriendsAtAll) "No friends yet" else "No results found",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = if (noFriendsAtAll) "Friends you add will appear here."
        else "Try a different name or username.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}
