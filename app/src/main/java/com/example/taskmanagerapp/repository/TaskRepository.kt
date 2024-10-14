package com.example.taskmanagerapp.repository

import com.example.taskmanagerapp.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A singleton repository that manages tasks in memory.
 * It uses MutableStateFlow to expose the list of tasks as a flow,
 * which can be observed for changes by other parts of the app.
 */
@Singleton // Ensures a single instance of the repository is used throughout the app.
class TaskRepository @Inject constructor() { // Dependency injection with Hilt (Inject annotation)

    // In-memory list of tasks
    private val tasks = mutableListOf<Task>()

    // Backing MutableStateFlow for the list of tasks, initially empty
    private val _tasksFlow = MutableStateFlow<List<Task>>(emptyList())

    // Publicly exposed immutable StateFlow for observing tasks list changes
    val tasksFlow: StateFlow<List<Task>> = _tasksFlow

    /**
     * Adds a new task to the in-memory list and updates the flow with the new list.
     * @param task The task to be added.
     */
    fun addTask(task: Task) {
        tasks.add(task) // Add task to the list
        _tasksFlow.value = tasks.toList() // Update the flow with a new copy of the list
    }

    /**
     * Updates an existing task in the list and refreshes the flow with the updated list.
     * If the task with the given ID is found, it is replaced by the updated task.
     * @param updatedTask The task with updated data.
     */
    fun updateTask(updatedTask: Task) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id } // Find the index of the task by ID
        if (index != -1) {
            tasks[index] = updatedTask // Replace the old task with the updated one
            _tasksFlow.value = tasks.toList() // Update the flow with the new list
        }
    }

    /**
     * Retrieves a task by its unique ID.
     * @param id The ID of the task to find.
     * @return The task if found, or null if it doesn't exist.
     */
    fun getTaskById(id: Int): Task? {
        return tasks.find { it.id == id } // Find and return the task with the matching ID
    }
}
