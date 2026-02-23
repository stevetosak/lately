package com.tosak.lately.features.archivedstories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tosak.lately.core.ui.components.AppTopBar
import com.tosak.lately.core.ui.components.ScreenLoading
import com.tosak.lately.features.archivedstories.components.ArchivedStoriesGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchivedStoriesScreen(
  navController: NavController,
) {

  val viewModel: ArchivedStoriesViewModel = hiltViewModel()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  Scaffold(
    topBar = {
      AppTopBar(
        title = "Archived stories",
        navController = navController
      )
    },
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    if (uiState.isLoading) {
      ScreenLoading()
    } else {
      if (uiState.archivedStories.isEmpty()) {
        ArchivedStoriesEmpty(modifier = Modifier.padding(innerPadding))
      } else {
        ArchivedStoriesGrid(
          stories = uiState.archivedStories,
          navController = navController
        )
      }
    }

  }
}

@Composable
private fun ArchivedStoriesEmpty(modifier: Modifier = Modifier) {
  Box(
    modifier          = modifier.fillMaxSize(),
    contentAlignment  = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text  = "No archived stories",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text  = "Stories you archive will appear here.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}