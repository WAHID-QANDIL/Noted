package org.wahid.noted.feature_note.presentation.note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.use_case.NoteUseCases
import org.wahid.noted.feature_note.domain.utils.NoteOrder
import org.wahid.noted.feature_note.domain.utils.OrderType
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
) : ViewModel() {


    private var recentlyDeletedNote: Note? = null
    private val _state = mutableStateOf(NoteStates())
    val state: State<NoteStates> = _state
    private var getNotesJob:Job? = null


    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(events: NoteEvents) {

        when (events) {
            is NoteEvents.Order -> {
                if (state.value.noteOrder::class == events.noteOrder::class) {
                    if (state.value.noteOrder.orderType == events.noteOrder.orderType)
                        return
                }
                getNotes(events.noteOrder)
            }
            is NoteEvents.RestoreNote -> {
                viewModelScope.launch {
                    useCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }

            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote = events.note
                    useCases.deleteNote(events.note)
                }

            }

            is NoteEvents.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){

        getNotesJob?.cancel()

        getNotesJob = useCases.getAllNotes(noteOrder).onEach { listOfNotes->
            _state.value = state.value.copy(
                notes = listOfNotes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }

}