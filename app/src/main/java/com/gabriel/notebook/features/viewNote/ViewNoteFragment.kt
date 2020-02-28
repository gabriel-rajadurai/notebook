package com.gabriel.notebook.features.viewNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.common.setActionBarTitle
import com.gabriel.notebook.common.showBackButton
import kotlinx.android.synthetic.main.view_note_fragment.*
import kotlinx.coroutines.launch

class ViewNoteFragment : BaseFragment() {

    //Lazily initialize the viewModel using the viewModels() delegate
    private val viewNoteViewModel: ViewNoteViewModel by viewModels {
        ViewModelFactory {
            ViewNoteViewModel(requireActivity().application)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(BUN_NOTE_ID)?.let {
            lifecycleScope.launch {
                viewNoteViewModel.getNoteById(it)?.let { note ->
                    setActionBarTitle(note.title)
                    tvNote.text = note.content
                }
            }
        } ?: throw IllegalStateException("Note ID cannot be null")
        showBackButton()
    }

    companion object {
        const val BUN_NOTE_ID = "bunNoteId"
    }
}
