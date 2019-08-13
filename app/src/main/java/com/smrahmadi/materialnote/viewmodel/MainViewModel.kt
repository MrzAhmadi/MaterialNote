package com.smrahmadi.materialnote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.smrahmadi.materialnote.dagger.component.DaggerAppComponent
import com.smrahmadi.materialnote.dagger.module.AppModule
import com.smrahmadi.materialnote.dagger.module.RoomModule
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .roomModule(RoomModule(application))
            .build()
            .inject(this)
        allNotes = repository.allNotes
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}