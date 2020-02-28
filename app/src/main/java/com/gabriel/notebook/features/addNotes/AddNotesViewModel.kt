package com.gabriel.notebook.features.addNotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gabriel.data.models.Note
import com.gabriel.data.repositories.NotesRepository
import com.gabriel.notebook.R
import com.gabriel.notebook.common.DATE_FORMAT
import com.gabriel.notebook.common.getString
import com.gabriel.notebook.common.toFormat
import java.util.*

class AddNotesViewModel(app: Application) : AndroidViewModel(app) {

    val title = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
    val titleError = MutableLiveData<String>()
    val noteError = MutableLiveData<String>()

    private val notesRepository by lazy { NotesRepository(getApplication()) }

    suspend fun saveNote(): Int {
        titleError.value = null
        noteError.value = null
        var areFieldsValid = true
        if (title.value.isNullOrBlank()) {
            titleError.value = getString(R.string.error_enter_title)
            areFieldsValid = false
        }
        if (notes.value.isNullOrBlank()) {
            noteError.value = getString(R.string.error_enter_note)
            areFieldsValid = false
        }
        if (!areFieldsValid) return 0
        return notesRepository.saveNote(
            Note(
                title = title.value.toString(),
                content = notes.value.toString(),
                createdAt = Date().toFormat(DATE_FORMAT)
            )
        )
    }
}
