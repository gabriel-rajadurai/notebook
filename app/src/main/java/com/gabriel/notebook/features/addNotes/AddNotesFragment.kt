package com.gabriel.notebook.features.addNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.gabriel.notebook.R
import com.gabriel.notebook.common.ViewModelFactory

class AddNotesFragment : Fragment() {

    private val addNotesViewModel: AddNotesViewModel by viewModels {
        ViewModelFactory {
            AddNotesViewModel(requireActivity().application)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_notes_fragment, container, false)
    }
}
