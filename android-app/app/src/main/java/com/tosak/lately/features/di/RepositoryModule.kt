package com.tosak.lately.features.di
import com.tosak.lately.features.stories.repository.FakeStoryRepository
import com.tosak.lately.features.stories.repository.StoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStoryRepository(
        fakeStoryRepository: FakeStoryRepository
    ): StoryRepository
}