package org.wahid.noted.feature_note.domain.use_case

import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {


    suspend operator fun invoke(note: Note){
        return repository.deleteNote(note)
    }

}