package com.tosak.lately.features.archivedstories

import com.tosak.lately.features.stories.MediaType
import com.tosak.lately.features.stories.StoryLocation
import com.tosak.lately.features.stories.StoryVisibility
import java.time.Instant

data class ArchivedStory(
  val id: String,

  // Content
  val mediaUrl: String,
  val mediaType: MediaType,
  val caption: String?,
  val musicTrackId: String?,

  // Location
  val location: StoryLocation,

  // Metadata
  val createdAt: Instant,
  val expiredAt: Instant,
  val viewCount: Int = 0,
  val viewers: List<String> = emptyList(),
  val reactions: Map<String, String> = emptyMap(),

  // Visibility
  val visibility: StoryVisibility = StoryVisibility.PUBLIC,
)