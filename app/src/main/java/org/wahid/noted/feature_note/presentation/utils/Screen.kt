package org.wahid.noted.feature_note.presentation.utils

sealed class Screen(val route:String) {
    data object NoteScreen:Screen("note_screen")
    data object AddEditNoteScreen:Screen("add_edit_note_screen")
}