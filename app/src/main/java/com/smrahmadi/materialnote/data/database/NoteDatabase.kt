package com.smrahmadi.materialnote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.smrahmadi.materialnote.data.dao.NoteDao
import com.smrahmadi.materialnote.data.model.Note
import kotlinx.coroutines.runBlocking

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "YourDatabase.db")
                .addCallback(seedDatabaseCallback(context))
                .build()

        private fun seedDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        val noteDao = getInstance(context).noteDao()
                        val note = Note(title = "This is title", description = "This is Description")
                        runBlocking {
                            noteDao.insert(note)
                            noteDao.insert(note)
                            noteDao.insert(note)
                        }
                    }
                }
            }
        }
    }
}