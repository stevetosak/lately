package com.tosak.lately.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tosak.lately.core.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
  private val searchRepository: SearchRepository
) : ViewModel() {

  private val _query = MutableStateFlow("")
  private val _isLoading = MutableStateFlow(false)
  private val _errorMessage = MutableStateFlow<String?>(null)

  val uiState = combine(
    _query,
    searchRepository.results,
    searchRepository.searchHistory,
    _isLoading,
    _errorMessage
  ) { query, results, history, loading, error ->
    SearchUiState(
      query = query,
      results = results,
      searchHistory = history,
      isLoading = loading,
      errorMessage = error
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = SearchUiState()
  )

  init {
    _query
      .debounce(1000)
      .onEach { query ->
        viewModelScope.launch {
          performSearch(query)
        }
      }
      .launchIn(viewModelScope)
  }

  fun onQueryChange(query: String) {

    _query.value = query
    _errorMessage.value = null

    if (query.isBlank()) {
      searchRepository.clearResults()
    } else {
      _isLoading.value = true
    }
  }

  fun onHistoryItemClick(query: String) {
    onQueryChange(query)
  }

  fun removeHistoryItem(query: String) {
    viewModelScope.launch {
      searchRepository.removeFromHistory(query)
    }
  }

  fun clearHistory() {
    viewModelScope.launch {
      searchRepository.clearHistory()
    }
  }

  private fun performSearch(query: String) {

    if (query.isBlank()) {
      return
    }

    viewModelScope.launch {
      searchRepository.searchUsers(query)
        .onFailure { _errorMessage.value = "Search failed. Please try again." }

      _isLoading.value = false
    }
  }
}