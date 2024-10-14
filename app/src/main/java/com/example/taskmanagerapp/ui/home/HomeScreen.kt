package com.example.taskmanagerapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmanagerapp.model.Task
import com.example.taskmanagerapp.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class) // Opt-in for experimental Material3 API usage.
@Composable
fun HomeScreen(
    onAddTask: () -> Unit,  // Callback for when the "Add Task" button is clicked.
    onTaskClick: (Int) -> Unit,  // Callback for when a task item is clicked.
    viewModel: HomeViewModel = hiltViewModel()  // Using Hilt to inject the HomeViewModel.
) {
    // Collects the state of tasks from the ViewModel as a State object.
    val tasks = viewModel.tasks.collectAsState()

    // Scaffold provides the basic screen structure with a top bar and a floating action button.
    Scaffold(
        topBar = {
            // Top app bar with the title "Task Manager".
            TopAppBar(
                title = { Text("Task Manager") }
            )
        },
        floatingActionButton = {
            // Floating action button with an "Add" icon.
            FloatingActionButton(
                onClick = onAddTask,  // Triggers the callback for adding a task.
                modifier = Modifier.semantics { contentDescription = "Add Task" } // Accessibility label for screen readers.
            ) {
                Icon(Icons.Default.Add, contentDescription = null) // The "Add" icon.
            }
        }
    ) { paddingValues ->
        // LazyColumn is used to display a vertically scrolling list of tasks.
        LazyColumn(
            contentPadding = paddingValues,  // Padding around the content.
            modifier = Modifier.fillMaxSize()  // The column takes up the full available space.
        ) {
            // For each task in the list, create a TaskItem composable.
            items(tasks.value) { task ->
                TaskItem(task = task, onClick = { onTaskClick(task.id) }) // Passes task ID when clicked.
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    // Card represents a single task item with a clickable surface.
    Card(
        modifier = Modifier
            .fillMaxWidth()  // The card takes the full width of the parent.
            .padding(8.dp)   // Adds padding around the card.
            .clickable(onClick = onClick)  // Makes the card clickable.
            .semantics { contentDescription = "Task Item" } // Accessibility label for screen readers.
    ) {
        // A row layout inside the card, holding the task title and a checkbox.
        Row(modifier = Modifier.padding(16.dp)) {
            // Display the task's title text.
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleLarge,  // Text styling for large titles.
                modifier = Modifier.weight(1f)  // Takes up available space next to the checkbox.
            )
            // Checkbox to indicate if the task is completed.
            Checkbox(
                checked = task.isCompleted,  // Shows whether the task is completed.
                onCheckedChange = null,  // The checkbox is non-interactive here (can be customized).
                modifier = Modifier.semantics { contentDescription = "Task Completed Checkbox" } // Accessibility label.
            )
        }
    }
}
