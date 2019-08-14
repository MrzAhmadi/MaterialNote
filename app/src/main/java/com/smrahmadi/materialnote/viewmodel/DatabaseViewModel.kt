package com.smrahmadi.materialnote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smrahmadi.materialnote.data.repository.NoteRepository

class DatabaseViewModel(
    private var noteRepository: NoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteRepository::class.java)
            .newInstance(noteRepository)
    }
}