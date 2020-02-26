package com.gabriel.notebook.base

import androidx.fragment.app.Fragment

// Base Fragment to handle common logic
abstract class BaseFragment : Fragment() {

    /**
     * This is a utility method to control back navigation in fragments.
     * It will be called when user presses the device back button or
     * if the user presses the action bar back button
     */
    fun shouldGoBack() = true
}