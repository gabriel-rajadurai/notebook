package com.gabriel.notebook.features.addNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.gabriel.notebook.R
import com.gabriel.notebook.base.BaseFragment
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.common.setActionBarTitle
import com.gabriel.notebook.common.showBackButton
import com.gabriel.notebook.common.showSnackBar
import com.gabriel.notebook.databinding.AddNotesFragmentBinding
import com.gabriel.notebook.features.viewNote.ViewNoteFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_notes_fragment.*
import kotlinx.coroutines.launch

class AddNotesFragment : BaseFragment() {

    private var binding: AddNotesFragmentBinding? = null
    private val addNotesViewModel: AddNotesViewModel by viewModels {
        ViewModelFactory {
            AddNotesViewModel(requireActivity().application)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddNotesFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.model = addNotesViewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        showBackButton()
        setActionBarTitle(getString(R.string.title_add_note))

        btnSaveNote.setOnClickListener {
            lifecycleScope.launch {
                val insertedNoteId = addNotesViewModel.saveNote()
                if (insertedNoteId == -1) { // Insertion failed
                    rootView.showSnackBar(getString(R.string.error_failed_to_save_note))
                    return@launch
                } else if (insertedNoteId == 0) { // Field validation failed
                    return@launch
                }
                // Configure navigation such that, when going to viewNotesFragment,
                // every fragment between notesListFragment and viewNotesFragment are removed from stack
                val navOptions =
                    NavOptions.Builder().setPopUpTo(R.id.notesListFragment, false).build()
                findNavController().navigate(R.id.viewNoteFragment, Bundle().apply {
                    putInt(ViewNoteFragment.BUN_NOTE_ID, insertedNoteId)
                }, navOptions)
            }
        }
    }

    override fun shouldGoBack(): Boolean {
        if (addNotesViewModel.title.value?.isNotEmpty() == true
            || addNotesViewModel.notes.value?.isNotEmpty() == true
        ) {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.title_discard_note))
                .setMessage(getString(R.string.msg_discard_note))
                .setPositiveButton(
                    getString(R.string.yes)
                ) { dialog, _ ->
                    dialog?.dismiss()
                    findNavController().navigateUp()
                }
                .setNegativeButton(
                    getString(R.string.no)
                ) { dialog, _ -> dialog?.dismiss() }
                .show()
            return false
        } else {
            return true
        }
    }
}
