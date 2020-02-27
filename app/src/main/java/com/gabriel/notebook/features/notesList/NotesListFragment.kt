package com.gabriel.notebook.features.notesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.common.hideBackButton
import com.gabriel.notebook.common.setActionBarTitle
import kotlinx.android.synthetic.main.notes_list_fragment.*

class NotesListFragment : BaseFragment() {

    // Lazily initializing NotesListViewModel using the viewModels() delegate
    private val notesListViewModel: NotesListViewModel by viewModels {
        ViewModelFactory {
            NotesListViewModel(requireActivity().application)
        }
    }
    private val notesAdapter by lazy { NotesListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.app_name))
        hideBackButton()
        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.addNotesFragment)
        }
        getAllNotes()
    }

    private fun getAllNotes() {
        rvNotes.layoutManager = LinearLayoutManager(requireContext())
        rvNotes.adapter = notesAdapter
    }

}
