package com.example.cleanarchitectureexample.presentation.navigation

import com.example.cleanarchitectureexample.common.Constants.NOTES_LIST_SCREEN
import com.example.cleanarchitectureexample.common.Constants.NOTE_DETAILS_SCREEN

sealed class Screen(val route: String) {
    object NoteListScreen: Screen(NOTES_LIST_SCREEN)
    object NoteDetailsScreen: Screen(NOTE_DETAILS_SCREEN)
}
