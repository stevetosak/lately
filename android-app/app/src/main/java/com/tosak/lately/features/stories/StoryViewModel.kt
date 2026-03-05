package com.tosak.lately.features.stories

import android.location.Location
import androidx.lifecycle.ViewModel
import com.tosak.lately.features.stories.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel() {
    
    fun getNearbyStories(location: Location, radius: Int): List<Story>{
        return storyRepository.getStories(location = location, radius = radius)
    }
}