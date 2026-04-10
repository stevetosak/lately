package com.tosak.lately.features.stories.viewer

import com.tosak.lately.features.stories.MediaType
import com.tosak.lately.features.stories.StoryVisibility
import java.time.Instant

data class StoryViewerItem(
    val id: String,
    val authorUsername: String?,
    val authorAvatarUrl: String?,

    val mediaUrl: String?,
    val mediaType: MediaType,
    val caption: String?,

    val placeName: String,

    val createdAt: Instant,
    val viewCount: Int = 0,
    val viewers: List<String> = emptyList(),
    val reactions: Map<String, String> = emptyMap(),

    val visibility: StoryVisibility
)
