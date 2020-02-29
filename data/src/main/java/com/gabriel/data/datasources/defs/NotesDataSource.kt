package com.gabriel.data.datasources.defs

import androidx.lifecycle.LiveData
import com.gabriel.data.models.Note

interface NotesDataSource {

    suspend fun saveNote(note: Note): Int

    fun getAllNotes(): LiveData<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun deleteNotes(vararg notes: Note): Int
}