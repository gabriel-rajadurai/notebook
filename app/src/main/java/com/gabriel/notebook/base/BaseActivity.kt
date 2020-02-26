package com.gabriel.notebook.base

import androidx.appcompat.app.AppCompatActivity

// Base Activity to handle common logic
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getCurrentFragment(): BaseFragment?

    override fun onSupportNavigateUp(): Boolean {
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
        getCurrentFragment()?.let {
            // This will give the back navigation control to the current fragment
            if (it.shouldGoBack())
                super.onBackPressed()
        } ?: super.onBackPressed()
    }
}