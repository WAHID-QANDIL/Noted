package org.wahid.noted.feature_note.domain.use_case

import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.repository.NoteRepository

class GetNoteById(private val repository: NoteRepository) {

    suspend operator fun invoke(noteId:Int): Note?{
        return repository.getNoteById(noteId)

    }


}