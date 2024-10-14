package com.example.taskmanagerapp.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmanagerapp.model.Task
import com.example.taskmanagerapp.viewmodel.TaskViewModel

// Opt-in for experimental Material 3 API features.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    onTaskSaved: () -> Unit, // Callback to execute after saving the task.
    viewModel: TaskViewModel = hiltViewModel() // Injects the TaskViewModel.
) {
    // State variables for task properties.
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            // Top app bar with screen title.
            TopAppBar(
                title = { Text("Create Task") }
            )
        }
    ) { paddingValues ->
        // Column layout to organize UI elements vertically with padding.
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Text field for task title.
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Task Title" },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Text field for task description.
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Task Description" }
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Text field for task due date.
            OutlinedTextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due Date") },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Task Due Date" },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Button to save the task.
            Button(
                onClick = {
                    // Construct a task object and save it via the viewModel.
                    val task = Task(
                        id = (0..100000).random(), // Randomly generate an ID for the task.
                        title = title,
                        description = description,
                        dueDate = dueDate
                    )
                    viewModel.saveTask(task)
                    onTaskSaved() // Execute callback after task is saved.
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Save Task Button" }
            ) {
                Text("Save Task")
            }
        }
    }
}
