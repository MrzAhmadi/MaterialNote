package com.smrahmadi.materialnote.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.smrahmadi.materialnote.data.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getAll(): LiveData<List<Note>>

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()

}