package com.tosak.lately.features.stories

import android.location.Location
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryRepositoryImpl @Inject constructor() : StoryRepository {
    override fun getStories(location: Location, radius: Int) {
        TODO("Not yet implemented")
    }
}