package com.example.taskmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.taskmanagerapp.navigation.NavGraph
import com.example.taskmanagerapp.ui.theme.TaskManagerTheme
import dagger.hilt.android.AndroidEntryPoint

// Marks this activity to be injectable by Hilt, and it will also inject dependencies into Android framework classes.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Opt-in marker to use experimental API from Material3 library for handling window size classes.
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of this activity using Compose UI toolkit.
        setContent {
            // Apply the custom material theme defined for this application.
            TaskManagerTheme {
                // Calculate the size class of the window this Compose UI is being drawn in. This helps adapting UI for different screen sizes.
                val windowSizeClass = calculateWindowSizeClass(this)
                // Setup the navigation graph for the app which manages Compose navigation between screens.
                NavGraph()
            }
        }
    }
}
