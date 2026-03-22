package com.tosak.lately.core.data.repository

import com.tosak.lately.features.archivedstories.ArchivedStory
import com.tosak.lately.features.archivedstories.data.allArchivedStories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArchivedStoriesRepository @Inject constructor() {

    private val _archivedStories = MutableStateFlow<List<ArchivedStory>>(emptyList())
    val archivedStories: StateFlow<List<ArchivedStory>> = _archivedStories.asStateFlow()

    suspend fun loadArchivedStories(): Result<Unit> = runCatching {
        _archivedStories.value = allArchivedStories
    }
}