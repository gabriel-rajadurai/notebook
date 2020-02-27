package com.gabriel.notebook.features.addNotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gabriel.data.models.Note
import com.gabriel.data.repositories.NotesRepository
import com.gabriel.notebook.common.DATE_FORMAT
import com.gabriel.notebook.common.toFormat
import java.util.*

class AddNotesViewModel(app: Application) : AndroidViewModel(app) {

    val title = MutableLiveData<String>()
    val notes = MutableLiveData<String>()

    private val notesRepository by lazy { NotesRepository(getApplication()) }

    suspend fun saveNote(): Int {
        return notesRepository.saveNote(
            Note(
                title = title.value.toString(),
                content = notes.value.toString(),
                createdAt = Date().toFormat(DATE_FORMAT)
            )
        )
    }
}
