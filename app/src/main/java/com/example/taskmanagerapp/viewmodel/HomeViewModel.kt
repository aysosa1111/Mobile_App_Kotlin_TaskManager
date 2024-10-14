package com.example.taskmanagerapp.viewmodel

// Importing required classes and packages
import androidx.lifecycle.ViewModel
import com.example.taskmanagerapp.model.Task
import com.example.taskmanagerapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * A ViewModel class annotated with @HiltViewModel, indicating it's a Hilt-aware ViewModel.
 * Hilt will handle its dependencies injection.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TaskRepository  // Injecting the TaskRepository dependency into the ViewModel
) : ViewModel() {
    // A StateFlow to hold and emit updates to the list of tasks. This is observed by the UI to get updates.
    val tasks: StateFlow<List<Task>> = repository.tasksFlow
}
