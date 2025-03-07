package org.wahid.noted.feature_note.presentation.add_edit_note_screen

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val message:String):AddEditNoteEvent()
    data class EnteredContent(val message: String):AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState):AddEditNoteEvent()
    data class ChangeColor(val color:Int):AddEditNoteEvent()
    data object SaveNote:AddEditNoteEvent()
}
