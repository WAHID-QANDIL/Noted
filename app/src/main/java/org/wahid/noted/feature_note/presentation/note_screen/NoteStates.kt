package org.wahid.noted.feature_note.presentation.note_screen

import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.utils.NoteOrder
import org.wahid.noted.feature_note.domain.utils.OrderType

data class NoteStates(
    val notes:List<Note> = emptyList(),
    val noteOrder:NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
