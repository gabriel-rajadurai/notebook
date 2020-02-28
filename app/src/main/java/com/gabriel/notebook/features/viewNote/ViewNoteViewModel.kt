package com.gabriel.notebook.features.viewNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gabriel.data.repositories.NotesRepository

class ViewNoteViewModel(app: Application) : AndroidViewModel(app) {

    private val notesRepository by lazy { NotesRepository(getApplication()) }

    suspend fun getNoteById(id: Int) = notesRepository.getNoteById(id)
}
