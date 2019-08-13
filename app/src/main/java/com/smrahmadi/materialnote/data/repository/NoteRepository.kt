package com.smrahmadi.materialnote.data.repository

import androidx.annotation.WorkerThread
import com.smrahmadi.materialnote.data.dao.NoteDao
import com.smrahmadi.materialnote.data.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    val allNotes = noteDao.getAll()

    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    @WorkerThread
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    @WorkerThread
    fun deleteAll() {
        noteDao.deleteAll()
    }

}