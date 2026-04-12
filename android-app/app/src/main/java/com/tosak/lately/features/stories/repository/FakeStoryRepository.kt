package com.tosak.lately.features.stories.repository

import allStories
import android.location.Location
import com.tosak.lately.features.stories.Story
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeStoryRepository @Inject constructor() : StoryRepository {

    override suspend fun getStories(location: Location, radius: Int): Result<List<Story>> = runCatching {
        allStories.filter { story ->
            val storyLocation = Location("").apply {
                latitude = story.location.latitude
                longitude = story.location.longitude
            }
            location.distanceTo(storyLocation) <= radius
        }
    }
}