package com.example.taskmanagerapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskmanagerapp.model.Task
import com.example.taskmanagerapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// HiltViewModel annotation is used for dependency injection in ViewModels.
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository // Inject TaskRepository to access data operations
) : ViewModel() {
    // Private mutable state flow to hold the current task, not exposed to other components.
    private val _task = MutableStateFlow<Task?>(null)

    // Public read-only state flow to be observed by the UI or other components.
    val task: StateFlow<Task?> = _task

    // Load a task by ID from the repository and update the state flow.
    fun loadTask(id: Int) {
        _task.value = repository.getTaskById(id)
    }

    // Save a task to the repository. If it doesn't exist, add it, otherwise update it.
    fun saveTask(task: Task) {
        if (repository.getTaskById(task.id) == null) {
            repository.addTask(task) // Add new task if it does not exist
        } else {
            repository.updateTask(task) // Update existing task
        }
    }
}
