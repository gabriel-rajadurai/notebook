package com.gabriel.notebook.features.viewNote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.gabriel.notebook.NotesApplication

import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.common.setActionBarTitle
import com.gabriel.notebook.common.showBackButton
import com.gabriel.notebook.common.showSnackBar
import com.gabriel.notebook.features.addNotes.AddNotesFragment
import kotlinx.android.synthetic.main.view_note_fragment.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewNoteFragment : BaseFragment() {

    @Inject
    lateinit var viewNoteViewModel: ViewNoteViewModel

    private val noteId by lazy {
        arguments?.getInt(BUN_NOTE_ID) ?: throw IllegalStateException("Note ID cannot be null")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireContext().applicationContext as NotesApplication).appGraph.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        lifecycleScope.launch {
            viewNoteViewModel.getNoteById(noteId)?.let { note ->
                setActionBarTitle(note.title)
                tvNote.text = note.content
            }
        }
        showBackButton()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_notes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionDelete) {
            lifecycleScope.launch {
                val deleteCount = viewNoteViewModel.deleteNote(noteId)
                if (deleteCount > 0) { // Delete successful
                    rootView.showSnackBar("Deleted successfully")
                    findNavController().navigateUp()
                } else {
                    rootView.showSnackBar(
                        getString(R.string.error_delete_note)
                    )
                }
            }
        } else if (item.itemId == R.id.actionEdit) {
            findNavController().navigate(R.id.addNotesFragment, Bundle().apply {
                putString(AddNotesFragment.BUN_ACTION, AddNotesFragment.ACTION_UPDATE)
                putInt(AddNotesFragment.BUN_NOTE_ID, noteId)
            }, NavOptions.Builder().setPopUpTo(R.id.notesListFragment, false).build())
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val BUN_NOTE_ID = "bunNoteId"
    }
}
