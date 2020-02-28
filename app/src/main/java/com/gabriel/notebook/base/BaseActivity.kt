package com.gabriel.notebook.base

import androidx.appcompat.app.AppCompatActivity
import com.gabriel.notebook.common.hideKeyboard

// Base Activity to handle common logic
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getCurrentFragment(): BaseFragment?

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        return getCurrentFragment()?.let {
            // This will give the back navigation control to the current fragment
            if (it.shouldGoBack()) {
                super.onBackPressed()
                super.onSupportNavigateUp()
            }
            else
                false
        } ?: super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        hideKeyboard()
        getCurrentFragment()?.let {
            // This will give the back navigation control to the current fragment
            if (it.shouldGoBack())
                super.onBackPressed()
        } ?: super.onBackPressed()
    }
}