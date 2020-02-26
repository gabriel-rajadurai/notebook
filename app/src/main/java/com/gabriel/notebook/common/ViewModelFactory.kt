package com.gabriel.notebook.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Factory class for instantiating viewModels.
// It takes in a creator() function as an argument to instantiate a viewmodel
class ViewModelFactory(
    val creator: () -> ViewModel
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}