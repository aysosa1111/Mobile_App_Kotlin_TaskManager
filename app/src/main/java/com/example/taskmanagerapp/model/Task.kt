package com.example.taskmanagerapp.model

/**
 * Data class representing a Task object.
 * This class defines the structure of a task, including its unique ID, title, description, due date, and completion status.
 */
data class Task(
    val id: Int,               // Unique identifier for the task
    val title: String,          // Title or name of the task
    val description: String,    // Detailed description of the task
    val dueDate: String,        // Due date for the task (formatted as a String)
    val isCompleted: Boolean = false // Flag indicating whether the task is completed (default is false)
)
