package com.gabriel.notebook.features.notesList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gabriel.data.repositories.NotesRepository
import javax.inject.Inject

class NotesListViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var notesRepository: NotesRepository

    fun getAllNotes() = notesRepository.getAllNotes()
}
