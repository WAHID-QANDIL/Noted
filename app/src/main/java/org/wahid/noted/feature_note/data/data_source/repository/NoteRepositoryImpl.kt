package org.wahid.noted.feature_note.data.data_source.repository

import kotlinx.coroutines.flow.Flow
import org.wahid.noted.feature_note.data.data_source.local.NoteDao
import org.wahid.noted.feature_note.domain.modle.Note
import org.wahid.noted.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
      return  dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
       return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}