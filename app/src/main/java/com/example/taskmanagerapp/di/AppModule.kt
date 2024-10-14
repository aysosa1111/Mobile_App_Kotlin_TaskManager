package com.example.taskmanagerapp.di

import com.example.taskmanagerapp.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// This class is annotated with @Module, making it a Dagger module where dependencies can be provided.
@Module
// @InstallIn specifies the Dagger component this module is installed into, in this case, the SingletonComponent.
// This ensures that the provided dependencies will have a singleton scope, existing only once across the entire application.
@InstallIn(SingletonComponent::class)
object AppModule {

    // This function provides an instance of TaskRepository as a singleton.
    // @Singleton annotation ensures that only one instance of TaskRepository will be created and used throughout the app.
    // @Provides annotation tells Dagger how to create and provide this dependency.
    @Singleton
    @Provides
    fun provideTaskRepository(): TaskRepository {
        // Creates and returns an instance of TaskRepository.
        return TaskRepository()
    }
}
