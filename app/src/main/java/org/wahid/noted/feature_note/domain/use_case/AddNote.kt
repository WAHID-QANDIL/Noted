package org.wahid.noted.feature_note.domain.use_case

import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(private val repository: NoteRepository) {

    @Throws(Note.Companion.InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (isValidNote(note)) {
            repository.insertNote(note)
        }
    }


    private fun isValidNote(note: Note): Boolean {
        if (note.title.isBlank())
            throw Note.Companion.InvalidNoteException("The title of the note can't be empty")

        if (note.content.isBlank() )
            throw Note.Companion.InvalidNoteException("The content of the note can't be empty")

        return true

    }


}