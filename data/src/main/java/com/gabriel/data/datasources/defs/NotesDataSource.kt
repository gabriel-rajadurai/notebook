package com.gabriel.data.datasources.defs

import com.gabriel.data.models.Note

interface NotesDataSource {
    suspend fun saveNote(note: Note)
}