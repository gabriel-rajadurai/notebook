package com.gabriel.notebook.features.viewNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gabriel.data.repositories.NotesRepository
import javax.inject.Inject

class ViewNoteViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var notesRepository: NotesRepository

    suspend fun getNoteById(id: Int) = notesRepository.getNoteById(id)

    suspend fun deleteNote(id: Int): Int {
        getNoteById(id)?.let {
            return notesRepository.deleteNotes(it)
        }
        return 0
    }
}
