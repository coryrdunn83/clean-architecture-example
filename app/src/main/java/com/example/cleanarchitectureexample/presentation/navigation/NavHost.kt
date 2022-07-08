package com.example.cleanarchitectureexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cleanarchitectureexample.presentation.note_details.NoteDetailsScreen
import com.example.cleanarchitectureexample.presentation.note_list.NoteListScreen

@Composable
fun CleanArchitectureNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.NoteListScreen.route) {
            NoteListScreen(navController = navController)
        }
        composable(
            route = Screen.NoteDetailsScreen.route + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(name = "noteColor") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            NoteDetailsScreen(navController = navController, noteColor = color)
        }
    }
}