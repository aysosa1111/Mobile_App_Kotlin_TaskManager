package com.example.taskmanagerapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskmanagerapp.ui.home.HomeScreen
import com.example.taskmanagerapp.ui.task.CreateTaskScreen
import com.example.taskmanagerapp.ui.task.EditTaskScreen

// The NavGraph function is responsible for defining and managing the navigation
// structure of the application using Jetpack Compose's Navigation component.
@Composable
fun NavGraph() {
    // Initialize the NavController to handle navigation actions between screens.
    val navController = rememberNavController()

    // NavHost defines the navigation graph for the app, connecting destinations (screens).
    // 'startDestination' specifies the initial screen to be displayed when the app starts.
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Define the "home" route, which renders the HomeScreen.
        // It also passes two lambdas: one for adding a task and one for handling task clicks.
        composable("home") {
            HomeScreen(
                onAddTask = { navController.navigate("createTask") },  // Navigates to CreateTask screen when adding a task
                onTaskClick = { taskId ->
                    navController.navigate("editTask/$taskId")  // Navigates to EditTask screen when a task is clicked
                }
            )
        }

        // Define the "createTask" route, which renders the CreateTaskScreen.
        // When a task is saved, it pops the back stack to return to the previous screen (HomeScreen).
        composable("createTask") {
            CreateTaskScreen(
                onTaskSaved = { navController.popBackStack() }  // Go back to the previous screen after saving a task
            )
        }

        // Define the "editTask" route, which accepts a taskId as an argument.
        // The argument is passed to the EditTaskScreen to edit a specific task.
        composable(
            route = "editTask/{taskId}",  // Route with a dynamic taskId argument
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })  // Declare taskId argument of type Int
        ) { backStackEntry ->
            // Retrieve the taskId from the back stack arguments.
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable

            // Render the EditTaskScreen with the given taskId, allowing the user to edit the task.
            // After editing, it navigates back to the previous screen (HomeScreen).
            EditTaskScreen(
                taskId = taskId,
                onTaskSaved = { navController.popBackStack() }  // Go back to the previous screen after editing a task
            )
        }
    }
}
