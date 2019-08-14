package com.smrahmadi.materialnote.dagger.module

import android.app.Application
import com.smrahmadi.materialnote.data.dao.NoteDao
import com.smrahmadi.materialnote.data.database.NoteDatabase
import com.smrahmadi.materialnote.data.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RoomModule(var application: Application) {

    @Provides
    fun providesRoomDatabase() = NoteDatabase.getInstance(application)

    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()


    @Provides
    fun noteRepository(noteDao: NoteDao) = NoteRepository(noteDao)

}