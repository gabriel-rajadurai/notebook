package com.gabriel.data

import android.app.Application
import androidx.room.Room
import com.gabriel.data.datasources.NotesDatabase
import com.gabriel.data.datasources.defs.NotesDataSource
import com.gabriel.data.datasources.impl.NotesLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(app: Application) {

    private val database: NotesDatabase by lazy {
        Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "Notes_Database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNotesDatabase(): NotesDatabase {
        return database
    }

    @Singleton
    @Provides
    fun provideNotesDao() = database.notesDao()

    @Singleton
    @Provides
    fun provideNotesDataSource(): NotesDataSource = NotesLocalDataSource(provideNotesDao())
}