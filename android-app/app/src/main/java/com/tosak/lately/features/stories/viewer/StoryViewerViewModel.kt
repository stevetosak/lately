package com.tosak.lately.features.stories.viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class StoryViewerUiState(
    val stories: List<StoryViewerItem> = emptyList()
)

@HiltViewModel
class StoryViewerViewModel @Inject constructor() : ViewModel() {

    private val _stories = MutableStateFlow<List<StoryViewerItem>>(emptyList())

    val uiState: StateFlow<StoryViewerUiState> = _stories
        .map { StoryViewerUiState(stories = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StoryViewerUiState()
        )

    fun load(stories: List<StoryViewerItem>) {
        _stories.value = stories
    }
}
