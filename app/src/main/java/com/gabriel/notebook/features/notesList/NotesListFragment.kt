package com.gabriel.notebook.features.notesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.ViewModelFactory

class NotesListFragment : BaseFragment() {

    // Lazily initializing NotesListViewModel using the viewModels() delegate
    private val notesListViewModel: NotesListViewModel by viewModels {
        ViewModelFactory {
            NotesListViewModel(requireActivity().application)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

}