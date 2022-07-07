package com.example.cleanarchitectureexample.presentation.note_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cleanarchitectureexample.domain.model.NoteOrder
import com.example.cleanarchitectureexample.domain.model.OrderType
import com.example.cleanarchitectureexample.presentation.common_components.CDRadioButton
import com.example.cleanarchitectureexample.presentation.note_list.Constants.NOTE_COLOR_TEXT
import com.example.cleanarchitectureexample.presentation.note_list.Constants.NOTE_DATE_TEXT
import com.example.cleanarchitectureexample.presentation.note_list.Constants.NOTE_SORT_ASCENDING_TEXT
import com.example.cleanarchitectureexample.presentation.note_list.Constants.NOTE_SORT_DESCENDING_TEXT
import com.example.cleanarchitectureexample.presentation.note_list.Constants.NOTE_TITLE_TEXT

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            CDRadioButton(
                text = NOTE_TITLE_TEXT,
                selected = noteOrder is NoteOrder.Title,
                onSelect = {
                    onOrderChange(NoteOrder.Title(noteOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CDRadioButton(
                text = NOTE_DATE_TEXT,
                selected = noteOrder is NoteOrder.Date,
                onSelect = {
                    onOrderChange(NoteOrder.Date(noteOrder.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CDRadioButton(
                text = NOTE_COLOR_TEXT,
                selected = noteOrder is NoteOrder.Color,
                onSelect = {
                    onOrderChange(NoteOrder.Color(noteOrder.orderType))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CDRadioButton(
                text = NOTE_SORT_ASCENDING_TEXT,
                selected = noteOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CDRadioButton(
                text = NOTE_SORT_DESCENDING_TEXT,
                selected = noteOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}