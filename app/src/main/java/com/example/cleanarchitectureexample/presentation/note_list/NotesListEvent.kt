package com.example.cleanarchitectureexample.presentation.note_list

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.model.NoteOrder

sealed class NotesListEvent {
    data class Order(val noteOrder: NoteOrder): NotesListEvent()
    data class DeleteNote(val note: Note): NotesListEvent()
    object RestoreNote: NotesListEvent()
    object ToggleOrderSection: NotesListEvent()
}
