package com.gabriel.notebook.features.viewNote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.common.setActionBarTitle
import com.gabriel.notebook.common.showBackButton
import com.gabriel.notebook.common.showSnackBar
import kotlinx.android.synthetic.main.view_note_fragment.*
import kotlinx.coroutines.launch

class ViewNoteFragment : BaseFragment() {

    //Lazily initialize the viewModel using the viewModels() delegate
    private val viewNoteViewModel: ViewNoteViewModel by viewModels {
        ViewModelFactory {
            ViewNoteViewModel(requireActivity().application)
        }
    }

    private val noteId by lazy {
        arguments?.getInt(BUN_NOTE_ID) ?: throw IllegalStateException("Note ID cannot be null")
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
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val BUN_NOTE_ID = "bunNoteId"
    }
}
