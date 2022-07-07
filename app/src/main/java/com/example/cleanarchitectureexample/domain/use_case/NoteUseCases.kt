package com.example.cleanarchitectureexample.domain.use_case

data class NoteUseCases(
    val getAllNotes: GetAllNotes,
    val getNoteById: GetNoteById,
    val addNote: AddNote,
    val deleteNote: DeleteNote
)
