package com.gabriel.data.datasources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabriel.data.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "Notes_Database"
                ).build()
                return INSTANCE!!
            }
        }
    }
}