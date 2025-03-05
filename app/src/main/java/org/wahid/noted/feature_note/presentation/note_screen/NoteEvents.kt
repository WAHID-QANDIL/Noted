package org.wahid.noted.feature_note.presentation.note_screen

import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.utils.NoteOrder

sealed class NoteEvents {
    data class Order(val noteOrder: NoteOrder) : NoteEvents()
    data class DeleteNote(val note: Note) : NoteEvents()
    data object RestoreNote : NoteEvents()
    data object ToggleOrderSection : NoteEvents()
}