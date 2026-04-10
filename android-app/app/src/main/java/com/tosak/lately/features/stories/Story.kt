package com.tosak.lately.features.stories

import java.time.Instant

data class Story(
    val id: String,
    val authorId: String,
    val authorUsername: String,
    val authorAvatarUrl: String?,

    // Content
    val mediaUrl: String,
    val mediaType: MediaType,
    val caption: String?,
    val musicTrackId: String?,

    // Location
    val location: StoryLocation,

    // Metadata
    val createdAt: Instant,
    val expiresAt: Instant,              // typically createdAt + 24h
    val viewCount: Int = 0,
    val viewers: List<String> = emptyList(),   // list of userIds
    val reactions: Map<String, String> = emptyMap(), // userId -> emoji/reaction

    // Visibility
    val visibility: StoryVisibility = StoryVisibility.PUBLIC,
    val isArchived: Boolean = false,
)

data class StoryLocation(
    val latitude: Double,
    val longitude: Double,
    val placeName: String,              // e.g. "Central Park"
    val city: String?,
    val country: String?,
    val geohash: String?,                // for efficient proximity queries
)

enum class MediaType {
    IMAGE, VIDEO
}

enum class StoryVisibility {
    PUBLIC,
    FOLLOWERS_ONLY,
}