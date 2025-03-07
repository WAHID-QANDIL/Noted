package org.wahid.noted.feature_note.domain.use_case

data class NoteUseCases(
    val deleteNote: DeleteNote,
    val getAllNotes: GetAllNotes,
    val addNote: AddNote,
    val getNoteById: GetNoteById
)
