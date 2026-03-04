package com.tosak.lately.features.stories

import android.location.Location

interface StoryRepository {
    fun getStories(location: Location,radius: Int)
}