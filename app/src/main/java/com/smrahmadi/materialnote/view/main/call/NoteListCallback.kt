package com.smrahmadi.materialnote.view.main.call

import com.smrahmadi.materialnote.data.model.Note

interface NoteListCallback {
    fun onItemClick(note: Note)
}