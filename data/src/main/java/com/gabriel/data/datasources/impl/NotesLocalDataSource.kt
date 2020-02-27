package com.gabriel.data.datasources.impl

import com.gabriel.data.datasources.NotesDao
import com.gabriel.data.datasources.defs.NotesDataSource
import com.gabriel.data.models.Note

class NotesLocalDataSource(private val notesDao: NotesDao) : NotesDataSource {

    override suspend fun saveNote(note: Note) {
        notesDao.saveNote(note)
    }
}