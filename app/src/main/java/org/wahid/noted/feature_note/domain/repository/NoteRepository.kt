package org.wahid.noted.feature_note.domain.repository

import kotlinx.coroutines.flow.Flow
import org.wahid.noted.feature_note.domain.modle.Note

interface NoteRepository {

    fun getAllNotes():Flow<List<Note>>

    suspend fun getNoteById(id:Int):Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)

}