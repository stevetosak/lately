package com.tosak.lately.features.search

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
import com.tosak.lately.features.search.components.SearchContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
  navController: NavController
) {
  val viewModel: SearchViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = {
      AppTopBar(
        title = "Search",
        navController = navController
      )
    },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    SearchContent(
      innerPadding = innerPadding,
      uiState = uiState,
      onQueryChange = viewModel::onQueryChange,
      onHistoryItemClick = viewModel::onHistoryItemClick,
      onRemoveHistoryItem = viewModel::removeHistoryItem,
      onClearHistory = viewModel::clearHistory,
      modifier = Modifier.fillMaxSize(),
      navController = navController
    )
  }
}