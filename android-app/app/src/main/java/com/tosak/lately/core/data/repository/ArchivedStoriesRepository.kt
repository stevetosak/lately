package com.tosak.lately.core.data.repository

import com.tosak.lately.features.archivedstories.ArchivedStory
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

    // TODO: replace with API/DB call
    _archivedStories.value = listOf(
      ArchivedStory(
        id = "1",
        title = "Sunset at the pier",
        createdAt = "Feb 12",
        locationLabel = "Santa Monica",
        thumbnailUrl = "https://images.unsplash.com/photo-1742802781124-8bd236001054?&auto=format&fit=cropw=400&h=700&q=80"
      ),
      ArchivedStory(
        id = "2",
        title = "Morning coffee run",
        createdAt = "Feb 9",
        locationLabel = "Silver Lake",
        thumbnailUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?auto=format&fit=crop&w=400&h=700&q=80"
      ),
      ArchivedStory(
        id = "3",
        title = "Street market vibes",
        createdAt = "Jan 30",
        locationLabel = "Downtown LA",
        thumbnailUrl = "https://images.unsplash.com/photo-1631733158391-3d3540795f09?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "4",
        title = "Rainy afternoon walk",
        createdAt = "Jan 22",
        locationLabel = "Echo Park",
        thumbnailUrl = "https://images.unsplash.com/photo-1601447599249-c408532111fa?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "5",
        title = "Rooftop golden hour",
        createdAt = "Jan 15",
        locationLabel = "Koreatown",
        thumbnailUrl = "https://plus.unsplash.com/premium_photo-1731950913696-2faa5d1ce554?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "6",
        title = "Beach volleyball day",
        createdAt = "Jan 10",
        locationLabel = "Venice Beach",
        thumbnailUrl = "https://images.unsplash.com/photo-1564695904932-9b5cae20083e?auto=format&fit=crop&w=400&h=700&q=80"
      ),
      ArchivedStory(
        id = "7",
        title = "City lights at night",
        createdAt = "Jan 5",
        locationLabel = "Downtown LA",
        thumbnailUrl = "https://images.unsplash.com/photo-1642212436391-d2a0bd200083?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "8",
        title = "Hiking to the sign",
        createdAt = "Dec 28",
        locationLabel = "Hollywood Hills",
        thumbnailUrl = "https://images.unsplash.com/photo-1524549028671-c64980386279?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "9",
        title = "Brunch with friends",
        createdAt = "Dec 21",
        locationLabel = "West Hollywood",
        thumbnailUrl = "https://plus.unsplash.com/premium_photo-1679932890605-99621535f591?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "10",
        title = "Skate park session",
        createdAt = "Dec 15",
        locationLabel = "Santa Monica",
        thumbnailUrl = "https://images.unsplash.com/photo-1651359838758-afa81efffdcd?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "11",
        title = "Desert road trip",
        createdAt = "Dec 5",
        locationLabel = "Joshua Tree",
        thumbnailUrl = "https://plus.unsplash.com/premium_photo-1730828573450-25dbca5aa17d?q=80&w=400&h=700&auto=format&fit=crop"
      ),
      ArchivedStory(
        id = "12",
        title = "Christmas market stroll",
        createdAt = "Nov 30",
        locationLabel = "LA Downtown",
        thumbnailUrl = "https://images.unsplash.com/photo-1576201018761-8952329c07df?q=80&w=400&h=700&auto=format&fit=crop"
      )
    )
  }
}