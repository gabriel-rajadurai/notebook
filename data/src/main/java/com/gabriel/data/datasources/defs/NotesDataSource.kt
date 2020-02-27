package com.gabriel.data.datasources.defs

import androidx.lifecycle.LiveData
import com.gabriel.data.models.Note

interface NotesDataSource {
    suspend fun saveNote(note: Note)
    fun getAllNotes(): LiveData<List<Note>>
}