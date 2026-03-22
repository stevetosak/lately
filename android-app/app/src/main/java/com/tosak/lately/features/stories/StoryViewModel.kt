package com.tosak.lately.features.stories

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.features.stories.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StoryUiState(
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
@HiltViewModel
class StoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoryUiState())
    val uiState: StateFlow<StoryUiState> = _uiState.asStateFlow()

    private var lastLocation: Location? = null

    fun loadNearbyStories(location: Location, radius: Int = 5000) {
        lastLocation = location

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            storyRepository.getStories(location = location, radius = radius)
                .onSuccess { stories ->
                    _uiState.update { it.copy(stories = stories, isLoading = false) }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false, errorMessage = "Failed to load stories") }
                }
        }
    }

    fun getCachedNearbyStories(): List<Story> {
        return uiState.value.stories
    }
}