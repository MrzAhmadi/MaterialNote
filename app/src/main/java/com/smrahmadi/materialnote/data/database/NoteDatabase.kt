package com.smrahmadi.materialnote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.data.dao.NoteDao
import com.smrahmadi.materialnote.data.database.NoteDatabase.Companion.VERSION
import com.smrahmadi.materialnote.data.model.Note
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.verbose

@Database(entities = [Note::class], version = VERSION)
abstract class NoteDatabase : RoomDatabase(), AnkoLogger {

    abstract fun noteDao(): NoteDao

    companion object {

        private val log = AnkoLogger(this.javaClass)
        private val logWithASpecificTag = AnkoLogger("NoteDatabase")

        const val VERSION = 1

        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "Note_database")
                .addCallback(seedDatabaseCallback(context))
                .build()

        private fun seedDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    log.verbose { "Create" }
                    ioThread {
                        val noteDao = getInstance(context).noteDao()
                        val note = Note(

                            title = context.getString(R.string.lorem_ipsum_title),
                            description = context.getString(R.string.lorem_ipsum_description)
                        )
                        runBlocking {
                            noteDao.insert(note)
                            noteDao.insert(note)
                            noteDao.insert(note)
                        }
                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    log.verbose { "Open" }
                }
            }
        }
    }
}