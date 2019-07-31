package com.smrahmadi.materialnote.data.dao

import androidx.room.*
import com.smrahmadi.materialnote.data.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAll(): List<Note>

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM note")
    fun deleteAll()

}