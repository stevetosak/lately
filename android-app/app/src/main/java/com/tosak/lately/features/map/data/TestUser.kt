package com.tosak.lately.features.map.data

import User
import UserLocation
import java.time.Instant
import java.time.temporal.ChronoUnit

val testUser: User = User(
    id = "user_001",
    username = "skopje_mila",
    displayName = "Mila Petrova",
    avatarUrl = "https://i.pravatar.cc/150?u=user_001",
    bio = "📍 Skopje | Coffee lover & city explorer ☕",

    location = UserLocation(
        latitude = 41.9961,
        longitude = 21.4316,
        geohash = "srx5vy",
        lastUpdatedAt = Instant.now().minus(5, ChronoUnit.MINUTES)
    ),
    isLocationVisible = true,
    lastSeenAt = Instant.now().minus(2, ChronoUnit.MINUTES),

    followerCount = 284,
    followingCount = 163,
    isFollowing = false,
    isFollowedBy = false,

    isVerified = false,
    isCurrentUser = true,
    accountCreatedAt = Instant.parse("2024-03-15T10:00:00Z")
)