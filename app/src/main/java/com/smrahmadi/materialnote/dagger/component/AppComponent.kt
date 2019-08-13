package com.smrahmadi.materialnote.dagger.component

import android.app.Application
import com.smrahmadi.materialnote.dagger.module.AppModule
import com.smrahmadi.materialnote.dagger.module.RoomModule
import com.smrahmadi.materialnote.data.dao.NoteDao
import com.smrahmadi.materialnote.data.database.NoteDatabase
import com.smrahmadi.materialnote.data.repository.NoteRepository
import com.smrahmadi.materialnote.viewmodel.MainViewModel
import com.smrahmadi.materialnote.viewmodel.NoteViewModel
import dagger.Component


@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)

    fun inject(noteViewModel: NoteViewModel)

    fun noteDao(): NoteDao

    fun noteDatabase(): NoteDatabase

    fun noteRepository(): NoteRepository

    fun application(): Application
}