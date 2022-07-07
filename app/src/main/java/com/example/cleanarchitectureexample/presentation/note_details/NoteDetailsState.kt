package com.example.cleanarchitectureexample.presentation.note_details

data class NoteDetailsState(
    val text: String = "",
    val hint: String = "",
    val showHint: Boolean = true
)