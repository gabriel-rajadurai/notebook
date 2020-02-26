package com.gabriel.notebook.features.addNotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class AddNotesViewModel(app: Application) : AndroidViewModel(app) {

    val title = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
}
