package com.example.cleanarchitectureexample.presentation.note_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureexample.domain.model.InvalidNoteException
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.use_case.NoteUseCases
import com.example.cleanarchitectureexample.presentation.note_details.Constants.NOTE_DETAILS_CONTENT_HINT
import com.example.cleanarchitectureexample.presentation.note_details.Constants.NOTE_DETAILS_SAVE_NOTE_ERROR
import com.example.cleanarchitectureexample.presentation.note_details.Constants.NOTE_DETAILS_TITLE_HINT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _noteTitleState = mutableStateOf(
        NoteDetailsState(hint = NOTE_DETAILS_TITLE_HINT)
    )
    val noteTitleState: State<NoteDetailsState> = _noteTitleState

    private val _noteContentState = mutableStateOf(
        NoteDetailsState(hint = NOTE_DETAILS_CONTENT_HINT)
    )
    val noteContentState: State<NoteDetailsState> = _noteContentState

    private val _noteColorState = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColorState: State<Int> = _noteColorState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteById(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitleState.value = noteTitleState.value.copy(
                            text = note.title,
                            showHint = false
                        )
                        _noteContentState.value = noteContentState.value.copy(
                            text = note.content,
                            showHint = false
                        )
                        _noteColorState.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteDetailsEvent) {
        when(event) {
            is NoteDetailsEvent.EnteredTitle -> {
                _noteTitleState.value = noteTitleState.value.copy(text = event.value)
            }
            is NoteDetailsEvent.ChangeTitleFocus -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    showHint = !event.focusState.isFocused && noteTitleState.value.text.isBlank()
                )
            }
            is NoteDetailsEvent.EnteredContent -> {
                _noteContentState.value = noteContentState.value.copy(text = event.value)
            }
            is NoteDetailsEvent.ChangeContentFocus -> {
                _noteContentState.value = noteContentState.value.copy(
                    showHint = !event.focusState.isFocused && _noteContentState.value.text.isBlank()
                )
            }
            is NoteDetailsEvent.ChangeColor -> {
                _noteColorState.value = event.color
            }
            is NoteDetailsEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = noteTitleState.value.text,
                                content = noteContentState.value.text,
                                timeStamp = System.currentTimeMillis(),
                                color = noteColorState.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: NOTE_DETAILS_SAVE_NOTE_ERROR
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }

}