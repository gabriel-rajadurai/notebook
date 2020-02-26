package com.gabriel.notebook.features.addNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gabriel.notebook.common.ViewModelFactory
import com.gabriel.notebook.databinding.AddNotesFragmentBinding

class AddNotesFragment : Fragment() {

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
    }
}
