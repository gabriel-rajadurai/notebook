package com.gabriel.notebook

import android.os.Bundle
import com.gabriel.notebook.base.BaseActivity
import com.gabriel.notebook.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun getCurrentFragment(): BaseFragment? {
        // Getting current fragment
        val fragmentsInStack = supportFragmentManager
            .findFragmentById(R.id.mainHost)?.childFragmentManager?.fragments
        if (fragmentsInStack.isNullOrEmpty()) return null
        val currentFragment = fragmentsInStack[0]
        if (currentFragment is BaseFragment) return currentFragment
        return null
    }
}
