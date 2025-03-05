package org.wahid.noted.feature_note.presentation.note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.use_case.NoteUseCases
import javax.inject.Inject


class NoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
) : ViewModel() {


    private var recentlyDeletedNote: Note? = null
    private val _state = mutableStateOf(NoteStates())
    private val state: State<NoteStates> = _state

    fun onEvent(noteEvents: NoteEvents) {

        when (noteEvents) {
            is NoteEvents.Order -> {
                


            }
            is NoteEvents.RestoreNote -> {
                viewModelScope.launch {
                    useCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }

            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote = noteEvents.note
                    useCases.deleteNote(noteEvents.note)
                }

            }

            is NoteEvents.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }
    }

}