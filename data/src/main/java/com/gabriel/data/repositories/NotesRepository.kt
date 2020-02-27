package com.gabriel.data.repositories

import android.content.Context
import com.gabriel.data.datasources.NotesDatabase
import com.gabriel.data.datasources.defs.NotesDataSource
import com.gabriel.data.datasources.impl.NotesLocalDataSource
import com.gabriel.data.models.Note

class NotesRepository(context: Context) : NotesDataSource {

    private val notesDataSource by lazy {
        NotesLocalDataSource(NotesDatabase.getDatabase(context).notesDao())
    }

    override suspend fun saveNote(note: Note) = notesDataSource.saveNote(note)

    override fun getAllNotes() = notesDataSource.getAllNotes()

    override suspend fun getNoteById(id: Int) = notesDataSource.getNoteById(id)
}