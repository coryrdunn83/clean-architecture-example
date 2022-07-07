package com.example.cleanarchitectureexample.presentation.note_list

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.model.NoteOrder
import com.example.cleanarchitectureexample.domain.model.OrderType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
