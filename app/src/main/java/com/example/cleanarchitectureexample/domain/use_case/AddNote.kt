package com.example.cleanarchitectureexample.domain.use_case

import com.example.cleanarchitectureexample.domain.model.InvalidNoteException
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Please enter a title for the note.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Please enter some content for the note.")
        }
        repository.insertNote(note)
    }
}