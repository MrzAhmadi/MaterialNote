package com.smrahmadi.materialnote.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smrahmadi.materialnote.data.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAll(): List<Note>

    @Query("DELETE FROM note")
    fun deleteAll()
}