package com.tosak.lately.features.stories.data

import com.tosak.lately.features.stories.MediaType
import com.tosak.lately.features.stories.Story
import com.tosak.lately.features.stories.StoryLocation
import com.tosak.lately.features.stories.StoryVisibility
import java.time.Instant
import java.time.temporal.ChronoUnit


val allStories = listOf(

    // --- Skopje Stories ---
    Story(
        id = "story_001",
        authorId = "user_001",
        authorUsername = "skopje_mila",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_001",
        mediaUrl = "https://picsum.photos/seed/story1/400/700",
        mediaType = MediaType.IMAGE,
        caption = "Morning walk by the river 🌊",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 41.9961,
            longitude = 21.4316,
            placeName = "Macedonia Square",
            city = "Skopje",
            country = "MK",
            geohash = "srx5vy"
        ),
        createdAt = Instant.now().minus(1, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(23, ChronoUnit.HOURS),
        viewCount = 34,
        viewers = listOf("user_002", "user_003"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_002",
        authorId = "user_002",
        authorUsername = "old_bazaar_guy",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_002",
        mediaUrl = "https://picsum.photos/seed/story2/400/700",
        mediaType = MediaType.IMAGE,
        caption = "Fresh burek from the čaršija 🥐",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 41.9981,
            longitude = 21.4338,
            placeName = "Old Bazaar",
            city = "Skopje",
            country = "MK",
            geohash = "srx5vz"
        ),
        createdAt = Instant.now().minus(3, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(21, ChronoUnit.HOURS),
        viewCount = 89,
        viewers = listOf("user_001", "user_004"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_003",
        authorId = "user_003",
        authorUsername = "kale_fortress_fan",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_003",
        mediaUrl = "https://picsum.photos/seed/story3/400/700",
        mediaType = MediaType.VIDEO,
        caption = "View from Kale fortress is unmatched 🏰",
        musicTrackId = "track_001",
        location = StoryLocation(
            latitude = 42.0010,
            longitude = 21.4317,
            placeName = "Kale Fortress",
            city = "Skopje",
            country = "MK",
            geohash = "srx5w0"
        ),
        createdAt = Instant.now().minus(2, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(22, ChronoUnit.HOURS),
        viewCount = 210,
        viewers = listOf("user_001", "user_002", "user_005"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_004",
        authorId = "user_004",
        authorUsername = "city_park_ana",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_004",
        mediaUrl = "https://picsum.photos/seed/story4/400/700",
        mediaType = MediaType.IMAGE,
        caption = "Sunset in City Park 🌅",
        musicTrackId = "track_002",
        location = StoryLocation(
            latitude = 41.9889,
            longitude = 21.4201,
            placeName = "City Park",
            city = "Skopje",
            country = "MK",
            geohash = "srx5uu"
        ),
        createdAt = Instant.now().minus(4, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(20, ChronoUnit.HOURS),
        viewCount = 56,
        viewers = listOf("user_003"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_005",
        authorId = "user_005",
        authorUsername = "chair_lift_stefan",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_005",
        mediaUrl = "https://picsum.photos/seed/story5/400/700",
        mediaType = MediaType.VIDEO,
        caption = "Riding up Vodno mountain ⛰️",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 41.9706,
            longitude = 21.3947,
            placeName = "Vodno Mountain",
            city = "Skopje",
            country = "MK",
            geohash = "srx5mn"
        ),
        createdAt = Instant.now().minus(6, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(18, ChronoUnit.HOURS),
        viewCount = 143,
        viewers = listOf("user_001", "user_002"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_006",
        authorId = "user_006",
        authorUsername = "matka_explorer",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_006",
        mediaUrl = "https://picsum.photos/seed/story6/400/700",
        mediaType = MediaType.IMAGE,
        caption = "Canyon Matka never gets old 🛶",
        musicTrackId = "track_003",
        location = StoryLocation(
            latitude = 41.9567,
            longitude = 21.3167,
            placeName = "Canyon Matka",
            city = "Skopje",
            country = "MK",
            geohash = "srx4rw"
        ),
        createdAt = Instant.now().minus(7, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(17, ChronoUnit.HOURS),
        viewCount = 301,
        viewers = listOf("user_002", "user_004", "user_005"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_007",
        authorId = "user_007",
        authorUsername = "aerodrom_local",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_007",
        mediaUrl = "https://picsum.photos/seed/story7/400/700",
        mediaType = MediaType.IMAGE,
        caption = "New coffee spot just opened here ☕",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 41.9756,
            longitude = 21.4689,
            placeName = "Aerodrom",
            city = "Skopje",
            country = "MK",
            geohash = "srx5qt"
        ),
        createdAt = Instant.now().minus(1, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(23, ChronoUnit.HOURS),
        viewCount = 12,
        viewers = emptyList(),
        visibility = StoryVisibility.PUBLIC
    ),

    // --- Rest of Macedonia ---
    Story(
        id = "story_008",
        authorId = "user_008",
        authorUsername = "ohrid_sunset",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_008",
        mediaUrl = "https://picsum.photos/seed/story8/400/700",
        mediaType = MediaType.IMAGE,
        caption = "Lake Ohrid in October is pure magic 🌊",
        musicTrackId = "track_004",
        location = StoryLocation(
            latitude = 41.1231,
            longitude = 20.8016,
            placeName = "Lake Ohrid",
            city = "Ohrid",
            country = "MK",
            geohash = "srmsyh"
        ),
        createdAt = Instant.now().minus(8, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(16, ChronoUnit.HOURS),
        viewCount = 512,
        viewers = listOf("user_001", "user_003", "user_007"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_009",
        authorId = "user_009",
        authorUsername = "bitola_chronicles",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_009",
        mediaUrl = "https://picsum.photos/seed/story9/400/700",
        mediaType = MediaType.VIDEO,
        caption = "Sirok Sokak on a Friday night 🎶",
        musicTrackId = "track_005",
        location = StoryLocation(
            latitude = 41.0297,
            longitude = 21.3297,
            placeName = "Širok Sokak",
            city = "Bitola",
            country = "MK",
            geohash = "srm7xv"
        ),
        createdAt = Instant.now().minus(5, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(19, ChronoUnit.HOURS),
        viewCount = 78,
        viewers = listOf("user_002", "user_006"),
        visibility = StoryVisibility.PUBLIC
    ),
    Story(
        id = "story_010",
        authorId = "user_010",
        authorUsername = "tetovo_hiker",
        authorAvatarUrl = "https://i.pravatar.cc/150?u=user_010",
        mediaUrl = "https://picsum.photos/seed/story10/400/700",
        mediaType = MediaType.IMAGE,
        caption = "Šarena Džamija is breathtaking 🕌",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 41.9994,
            longitude = 20.9716,
            placeName = "Šarena Džamija",
            city = "Tetovo",
            country = "MK",
            geohash = "srx1en"
        ),
        createdAt = Instant.now().minus(10, ChronoUnit.HOURS),
        expiresAt = Instant.now().plus(14, ChronoUnit.HOURS),
        viewCount = 190,
        viewers = listOf("user_001", "user_005"),
        visibility = StoryVisibility.PUBLIC
    ),
)