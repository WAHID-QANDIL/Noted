package org.wahid.noted.feature_note.presentation.add_edit_note_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.use_case.NoteUseCases
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Initialize with both the actual text and a hint
    private val _noteTitleState = mutableStateOf(
        NoteTextFieldState (hint = "Enter Title...")
    )
    val noteTitleState = _noteTitleState

    private val _noteContentState = mutableStateOf(
        NoteTextFieldState(hint = "Enter Some words")
    )
    val noteContentState = _noteContentState

    private val _noteColorState = mutableStateOf(Note.colors.random().toArgb())
    val noteColorState = _noteColorState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            viewModelScope.launch {
                if (noteId != -1) {
                    useCases.getNoteById(noteId)?.let { note ->
                        currentNoteId = note.id
                        // Update the text (content) and not the hint (title)
                        _noteTitleState.value = _noteTitleState.value.copy(
                            content = note.title,
                            isHintVisible = false
                        )
                        _noteContentState.value = _noteContentState.value.copy(
                            content = note.content,
                            isHintVisible = false
                        )
                        _noteColorState.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    // Check the content to decide whether to show the hint
                    isHintVisible = !event.focusState.isFocused && noteTitleState.value.content.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColorState.value = event.color
            }
            is AddEditNoteEvent.EnteredTitle -> {
                // Update the actual text content, not the hint text.
                _noteTitleState.value = noteTitleState.value.copy(
                    content = event.message
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContentState.value = noteContentState.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContentState.value.content.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContentState.value = noteContentState.value.copy(
                    content = event.message
                )
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        useCases.addNote(
                            note = Note(
                                title = noteTitleState.value.content,
                                content = noteContentState.value.content,
                                timestamp = System.currentTimeMillis(),
                                color = noteColorState.value,
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: Note.Companion.InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                e.localizedMessage ?: "Couldn't save an empty note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object SaveNote : UiEvent()
    }
}
