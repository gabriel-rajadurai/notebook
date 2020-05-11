package com.gabriel.data.datasources.impl

import com.gabriel.data.datasources.NotesDao
import com.gabriel.data.datasources.defs.NotesDataSource
import com.gabriel.data.models.Note
import javax.inject.Inject

class NotesLocalDataSource @Inject constructor(private val notesDao: NotesDao) : NotesDataSource {

    override suspend fun saveNote(note: Note) = notesDao.saveNote(note).toInt()

    override fun getAllNotes() = notesDao.getAllNotes()

    override suspend fun getNoteById(id: Int) = notesDao.getNoteById(id)

    override suspend fun deleteNotes(vararg notes: Note) = notesDao.deleteNotes(*notes)

}