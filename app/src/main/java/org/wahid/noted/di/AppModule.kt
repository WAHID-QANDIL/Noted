package org.wahid.noted.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.wahid.noted.feature_note.data.data_source.local.NoteDB
import org.wahid.noted.feature_note.data.data_source.repository.NoteRepositoryImpl
import org.wahid.noted.feature_note.domain.repository.NoteRepository
import org.wahid.noted.feature_note.domain.use_case.AddNote
import org.wahid.noted.feature_note.domain.use_case.DeleteNote
import org.wahid.noted.feature_note.domain.use_case.GetAllNotes
import org.wahid.noted.feature_note.domain.use_case.GetNoteById
import org.wahid.noted.feature_note.domain.use_case.NoteUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesNoteDatabase(application: Application):NoteDB
    {
        return Room.databaseBuilder(
            application,
            NoteDB::class.java,
            NoteDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDB):NoteRepository
    {
        return NoteRepositoryImpl(db.dao)
    }


    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getAllNotes = GetAllNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNoteById = GetNoteById(noteRepository)
        )
    }




}