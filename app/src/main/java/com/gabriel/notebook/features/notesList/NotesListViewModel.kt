package com.gabriel.notebook.features.notesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gabriel.data.repositories.NotesRepository

class NotesListViewModel(app: Application) : AndroidViewModel(app) {

    private val notesRepository by lazy { NotesRepository(getApplication()) }

    fun getAllNotes() = notesRepository.getAllNotes()
}
