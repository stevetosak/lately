package com.tosak.lately.features.archivedstories.data

import com.tosak.lately.features.archivedstories.ArchivedStory
import com.tosak.lately.features.stories.MediaType
import com.tosak.lately.features.stories.StoryLocation
import java.time.Instant
import java.time.temporal.ChronoUnit

val allArchivedStories = listOf(
    ArchivedStory(
        id = "1",
        mediaUrl = "https://images.unsplash.com/photo-1742802781124-8bd236001054?&auto=format&fit=crop&w=400&h=700&q=80",
        mediaType = MediaType.IMAGE,
        caption = "Sunset at the pier",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0100, longitude = -118.4960,
            placeName = "Santa Monica Pier",
            city = "Los Angeles", country = "USA",
            geohash = "9q5ctr"
        ),
        createdAt = Instant.now().minus(1, ChronoUnit.HOURS),
        expiredAt = Instant.now().plus(23, ChronoUnit.HOURS),
        viewCount = 34,
        viewers = listOf("user_002", "user_003")
    ),
    ArchivedStory(
        id = "2",
        mediaUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?auto=format&fit=crop&w=400&h=700&q=80",
        mediaType = MediaType.IMAGE,
        caption = "Morning coffee run",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0928, longitude = -118.2700,
            placeName = "Silver Lake",
            city = "Los Angeles", country = "USA",
            geohash = "9q5c7r"
        ),
        createdAt = Instant.now().minus(41, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(40, ChronoUnit.DAYS),
        viewCount = 12,
        viewers = listOf("user_004")
    ),
    ArchivedStory(
        id = "3",
        mediaUrl = "https://images.unsplash.com/photo-1631733158391-3d3540795f09?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "Street market vibes",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0407, longitude = -118.2468,
            placeName = "Downtown LA",
            city = "Los Angeles", country = "USA",
            geohash = "9q5ctr"
        ),
        createdAt = Instant.now().minus(51, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(50, ChronoUnit.DAYS),
        viewCount = 20,
        viewers = listOf("user_005", "user_006")
    ),
    ArchivedStory(
        id = "4",
        mediaUrl = "https://images.unsplash.com/photo-1601447599249-c408532111fa?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "Rainy afternoon walk",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0782, longitude = -118.2606,
            placeName = "Echo Park",
            city = "Los Angeles", country = "USA",
            geohash = "9q5c7q"
        ),
        createdAt = Instant.now().minus(59, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(58, ChronoUnit.DAYS),
        viewCount = 15,
        viewers = listOf("user_007")
    ),
    ArchivedStory(
        id = "5",
        mediaUrl = "https://plus.unsplash.com/premium_photo-1731950913696-2faa5d1ce554?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "Rooftop golden hour",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0660, longitude = -118.3000,
            placeName = "Koreatown",
            city = "Los Angeles", country = "USA",
            geohash = "9q5c7t"
        ),
        createdAt = Instant.now().minus(66, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(65, ChronoUnit.DAYS),
        viewCount = 18,
        viewers = listOf("user_008", "user_009")
    ),
    ArchivedStory(
        id = "6",
        mediaUrl = "https://images.unsplash.com/photo-1564695904932-9b5cae20083e?auto=format&fit=crop&w=400&h=700&q=80",
        mediaType = MediaType.IMAGE,
        caption = "Beach volleyball day",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 33.9850, longitude = -118.4695,
            placeName = "Venice Beach",
            city = "Los Angeles", country = "USA",
            geohash = "9q5cpt"
        ),
        createdAt = Instant.now().minus(71, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(70, ChronoUnit.DAYS),
        viewCount = 25,
        viewers = listOf("user_010")
    ),
    ArchivedStory(
        id = "7",
        mediaUrl = "https://images.unsplash.com/photo-1642212436391-d2a0bd200083?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "City lights at night",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0407, longitude = -118.2468,
            placeName = "Downtown LA",
            city = "Los Angeles", country = "USA",
            geohash = "9q5ctr"
        ),
        createdAt = Instant.now().minus(76, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(75, ChronoUnit.DAYS),
        viewCount = 30,
        viewers = listOf("user_011", "user_012")
    ),
    ArchivedStory(
        id = "8",
        mediaUrl = "https://images.unsplash.com/photo-1524549028671-c64980386279?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "Hiking to the sign",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.1341, longitude = -118.3215,
            placeName = "Hollywood Hills",
            city = "Los Angeles", country = "USA",
            geohash = "9q5c8r"
        ),
        createdAt = Instant.now().minus(83, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(82, ChronoUnit.DAYS),
        viewCount = 22,
        viewers = listOf("user_013")
    ),
    ArchivedStory(
        id = "9",
        mediaUrl = "https://plus.unsplash.com/premium_photo-1679932890605-99621535f591?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "Brunch with friends",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0900, longitude = -118.3617,
            placeName = "West Hollywood",
            city = "Los Angeles", country = "USA",
            geohash = "9q5c9r"
        ),
        createdAt = Instant.now().minus(90, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(89, ChronoUnit.DAYS),
        viewCount = 28,
        viewers = listOf("user_014", "user_015")
    ),
    ArchivedStory(
        id = "10",
        mediaUrl = "https://images.unsplash.com/photo-1651359838758-afa81efffdcd?q=80&w=400&h=700&auto=format&fit=crop",
        mediaType = MediaType.IMAGE,
        caption = "Skate park session",
        musicTrackId = null,
        location = StoryLocation(
            latitude = 34.0100, longitude = -118.4960,
            placeName = "Santa Monica",
            city = "Los Angeles", country = "USA",
            geohash = "9q5ctr"
        ),
        createdAt = Instant.now().minus(96, ChronoUnit.DAYS),
        expiredAt = Instant.now().minus(95, ChronoUnit.DAYS),
        viewCount = 19,
        viewers = listOf("user_016")
    ),
)