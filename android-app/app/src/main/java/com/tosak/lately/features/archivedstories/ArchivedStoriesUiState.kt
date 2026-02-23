package com.tosak.lately.features.archivedstories

data class ArchivedStoriesUiState(
  val archivedStories: List<ArchivedStory> = emptyList(),
  val isLoading: Boolean = true,
  val errorMessage: String? = null
)

data class ArchivedStory(
  val id: String,
  val title: String,
  val thumbnailUrl: String? = null,
  val createdAt: String,
  val locationLabel: String
)