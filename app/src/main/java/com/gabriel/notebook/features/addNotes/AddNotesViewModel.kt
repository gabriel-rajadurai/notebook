package com.gabriel.notebook.features.addNotes

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
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

    val noteValidator = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            validateNote(text.toString())
        }
    }
    val titleValidator = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            validateTitle(text.toString())
        }
    }

    private val notesRepository by lazy { NotesRepository(getApplication()) }

    suspend fun saveNote(noteId: Int): Int {
        titleError.value = null
        noteError.value = null
        val areFieldsValid = validateTitle() && validateNote()
        if (!areFieldsValid) return 0

        return notesRepository.saveNote(
            Note(
                id = noteId,
                title = title.value.toString(),
                content = notes.value.toString(),
                createdAt = Date().toFormat(DATE_FORMAT)
            )
        )
    }

    private fun validateTitle(title: String? = this.title.value): Boolean {
        return if (title.isNullOrBlank()) {
            titleError.value = getString(R.string.error_enter_title)
            true
        } else {
            titleError.value = null
            false
        }
    }

    private fun validateNote(note: String? = this.notes.value): Boolean {
        return if (note.isNullOrBlank()) {
            noteError.value = getString(R.string.error_enter_note)
            true
        } else {
            noteError.value = null
            false
        }
    }

    suspend fun getNoteById(noteId: Int) = notesRepository.getNoteById(noteId)
}
