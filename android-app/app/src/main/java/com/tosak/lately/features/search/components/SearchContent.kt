package com.tosak.lately.features.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tosak.lately.core.ui.components.SearchBar
import com.tosak.lately.features.search.SearchUiState
import com.tosak.lately.navigation.Destinations

@Composable
fun SearchContent(
  innerPadding: PaddingValues,
  uiState: SearchUiState,
  onQueryChange: (String) -> Unit,
  onHistoryItemClick: (String) -> Unit,
  onRemoveHistoryItem: (String) -> Unit,
  onClearHistory: () -> Unit,
  modifier: Modifier = Modifier,
  navController: NavController
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(innerPadding)
  ) {
    SearchBar(
      searchText = uiState.query,
      onSearchTextChange = onQueryChange,
      placeholder = "Search people…",
      modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
    )

    when {
      uiState.isLoading -> Box(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator(modifier = Modifier.size(32.dp))
      }

      uiState.showHistory -> {
        if (uiState.searchHistory.isNotEmpty()) {
          SearchHistoryHeader(
            onClearAll = onClearHistory,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
          )
          LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            items(uiState.searchHistory, key = { it }) { historyItem ->
              SearchHistoryItem(
                query = historyItem,
                onClick = { onHistoryItemClick(historyItem) },
                onRemove = { onRemoveHistoryItem(historyItem) }
              )
            }
          }
        } else {
          SearchIdleState()
        }
      }

      uiState.results.isEmpty() -> SearchEmptyResults(query = uiState.query)

      else -> LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        items(uiState.results, key = { it.id }) { user ->
          SearchUserCard(
            user = user,
            onClick = {
              navController.navigate(Destinations.Profile.route(user.id))
            }
          )
        }
      }
    }
  }
}

@Composable
private fun SearchIdleState(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 80.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Icon(
        imageVector = Icons.Outlined.Search,
        contentDescription = null,
        modifier = Modifier.size(40.dp),
        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
      )
      Spacer(Modifier.height(4.dp))
      Text(
        text = "Find people",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = "Search by name or username.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}

@Composable
private fun SearchEmptyResults(query: String, modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 80.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text = "No results for $query",
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.SemiBold,
      color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = "Try a different name or username.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}