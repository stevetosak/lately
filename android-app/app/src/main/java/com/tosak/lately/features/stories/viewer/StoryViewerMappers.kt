package com.tosak.lately.features.stories.viewer

import com.tosak.lately.features.archivedstories.ArchivedStory
import com.tosak.lately.features.stories.Story

fun ArchivedStory.toViewerItem() = StoryViewerItem(
    id            = id,
    authorUsername = null,
    authorAvatarUrl = null,

    mediaUrl = mediaUrl,
    mediaType = mediaType,
    caption = caption,

    placeName = location.placeName,

    createdAt = createdAt,
    viewCount = viewCount,
    viewers = viewers,
    reactions = reactions,

    visibility = visibility
)

fun Story.toViewerItem() = StoryViewerItem(
    id            = id,
    authorUsername = authorUsername,
    authorAvatarUrl = authorAvatarUrl,

    mediaUrl = mediaUrl,
    mediaType = mediaType,
    caption = caption,

    placeName = location.placeName,

    createdAt = createdAt,
    viewCount = viewCount,
    viewers = viewers,
    reactions = reactions,

    visibility = visibility
)