package com.tosak.lately.features.archivedstories.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tosak.lately.features.archivedstories.ArchivedStory
import com.tosak.lately.navigation.Destinations

@Composable
fun ArchivedStoriesGrid(
  stories: List<ArchivedStory>,
  navController: NavController,
  modifier: Modifier = Modifier
) {

  val gridState = rememberLazyGridState()

  LazyVerticalGrid(
    state = gridState,
    columns = GridCells.Fixed(2),
    modifier = modifier
      .fillMaxSize()
      .padding(top = 56.dp),
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    items(stories, key = { it.id }) { story ->
      ArchivedStoryCard(
        story = story,
        onClick = {
          navController.navigate(
            Destinations.StoryViewer.route(story.id)
          )
        }
      )
    }
  }
}


