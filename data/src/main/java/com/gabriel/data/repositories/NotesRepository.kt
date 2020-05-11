package com.gabriel.data.repositories

import com.gabriel.data.datasources.defs.NotesDataSource
import com.gabriel.data.models.Note
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDataSource: NotesDataSource) :
    NotesDataSource {

    override suspend fun saveNote(note: Note) = notesDataSource.saveNote(note)

    override fun getAllNotes() = notesDataSource.getAllNotes()

    override suspend fun getNoteById(id: Int) = notesDataSource.getNoteById(id)

    override suspend fun deleteNotes(vararg notes: Note) = notesDataSource.deleteNotes(*notes)
}