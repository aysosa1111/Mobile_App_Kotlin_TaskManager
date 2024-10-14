package com.example.taskmanagerapp.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmanagerapp.model.Task // Import for Task model class
import com.example.taskmanagerapp.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskId: Int, // ID of the task to edit
    onTaskSaved: () -> Unit, // Callback executed after task is saved
    viewModel: TaskViewModel = hiltViewModel() // Inject TaskViewModel
) {
    // Load task details from ViewModel
    viewModel.loadTask(taskId)
    // Collect the task from the ViewModel's state flow
    val taskState = viewModel.task.collectAsState()

    // Return early if the task is null
    val task = taskState.value ?: return

    // State variables for task properties
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var isCompleted by remember { mutableStateOf(task.isCompleted) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Task") } // App bar title
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp) // General padding
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }, // Label for the title field
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Task Title" },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp)) // Spacing between elements
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") }, // Label for the description field
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Task Description" }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due Date") }, // Label for the due date field
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Task Due Date" },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.semantics { contentDescription = "Task Completed Checkbox" }
            ) {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it } // Checkbox to mark the task as completed
                )
                Text(text = "Completed")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val updatedTask = Task(
                        id = task.id,
                        title = title,
                        description = description,
                        dueDate = dueDate,
                        isCompleted = isCompleted
                    )
                    viewModel.saveTask(updatedTask) // Save the updated task
                    onTaskSaved() // Execute callback after saving
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Save Changes Button" }
            ) {
                Text("Save Changes") // Text displayed on the save button
            }
        }
    }
}
