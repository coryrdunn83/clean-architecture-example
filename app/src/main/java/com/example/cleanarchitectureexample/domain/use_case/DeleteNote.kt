package com.example.cleanarchitectureexample.domain.use_case

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}