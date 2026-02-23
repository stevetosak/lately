package com.tosak.lately.features.archivedstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.ArchivedStoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchivedStoriesViewModel @Inject constructor(
  private val archivedStoriesRepository: ArchivedStoriesRepository
) : ViewModel() {

  private val _isLoading = MutableStateFlow(true)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val uiState: StateFlow<ArchivedStoriesUiState> = combine(
    archivedStoriesRepository.archivedStories,
    _isLoading,
    _errorMessage
  ) { archivedStories, loading, error ->
    ArchivedStoriesUiState(
      archivedStories = archivedStories,
      isLoading = loading,
      errorMessage = error
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = ArchivedStoriesUiState()
  )

  init {
    viewModelScope.launch {
      _isLoading.value = true

      archivedStoriesRepository.loadArchivedStories()
        .onFailure { _errorMessage.value = "Failed to load archived stories" }

      _isLoading.value = false
    }
  }
}