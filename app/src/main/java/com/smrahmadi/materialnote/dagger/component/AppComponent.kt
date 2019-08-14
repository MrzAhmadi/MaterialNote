package com.smrahmadi.materialnote.dagger.component

import android.app.Application
import com.smrahmadi.materialnote.dagger.module.AppModule
import com.smrahmadi.materialnote.dagger.module.RoomModule
import com.smrahmadi.materialnote.data.dao.NoteDao
import com.smrahmadi.materialnote.data.database.NoteDatabase
import com.smrahmadi.materialnote.data.repository.NoteRepository
import com.smrahmadi.materialnote.view.main.MainActivity
import com.smrahmadi.materialnote.view.note.NoteActivity
import dagger.Component


@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(noteActivity: NoteActivity)

    fun noteDao(): NoteDao

    fun noteDatabase(): NoteDatabase

    fun noteRepository(): NoteRepository

    fun application(): Application
}