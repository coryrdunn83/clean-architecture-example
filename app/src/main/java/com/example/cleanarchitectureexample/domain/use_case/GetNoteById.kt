package com.example.cleanarchitectureexample.domain.use_case

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}