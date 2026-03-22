package com.tosak.lately.features.stories.repository

import android.location.Location
import com.tosak.lately.features.stories.Story

interface StoryRepository {
    suspend fun getStories(location: Location, radius: Int): Result<List<Story>>
}