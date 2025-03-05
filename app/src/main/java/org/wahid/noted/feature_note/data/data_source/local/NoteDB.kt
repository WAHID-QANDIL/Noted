package org.wahid.noted.feature_note.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wahid.noted.feature_note.domain.modle.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB:RoomDatabase() {
    abstract val dao:NoteDao

    companion object{
        const val DATABASE_NAME = "note_db"
    }
}