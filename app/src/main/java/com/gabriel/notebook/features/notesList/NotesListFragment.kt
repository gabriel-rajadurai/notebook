package com.gabriel.notebook.features.notesList

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.data.models.Note
import com.gabriel.notebook.NotesApplication
import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.SpacesItemDecoration
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.common.hideBackButton
import com.gabriel.notebook.common.setActionBarTitle
import com.gabriel.notebook.features.addNotes.AddNotesFragment
import com.gabriel.notebook.features.viewNote.ViewNoteFragment
import kotlinx.android.synthetic.main.notes_list_fragment.*
import javax.inject.Inject

class NotesListFragment : BaseFragment(), NotesListAdapter.NotesItemClickListener {

    @Inject
    lateinit var notesListViewModel: NotesListViewModel

    private val notesAdapter by lazy { NotesListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireContext().applicationContext as NotesApplication).appGraph.inject(this)
        super.onCreate(savedInstanceState)
        //Handling ACTION_PROCESS_TEXT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)?.let {
                if (it.isNotBlank()) {
                    findNavController().navigate(R.id.addNotesFragment, Bundle().apply {
                        putString(AddNotesFragment.BUN_ACTION, Intent.EXTRA_PROCESS_TEXT)
                        putString(AddNotesFragment.NOTE_PROCESS_TEXT, it.toString())
                    })
                }
            }
        }
        retainInstance = true
    }

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
        rvNotes.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        rvNotes.adapter = notesAdapter
        notesListViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) tvNoNotes.visibility = View.VISIBLE
            else tvNoNotes.visibility = View.GONE
            notesAdapter.submitList(it)
        })
    }

    override fun onItemClicked(note: Note) {
        findNavController().navigate(R.id.viewNoteFragment, Bundle().apply {
            putInt(ViewNoteFragment.BUN_NOTE_ID, note.id)
        })
    }

}
