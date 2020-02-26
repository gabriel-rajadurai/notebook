package com.gabriel.notebook

import android.os.Bundle
import com.gabriel.notebook.base.BaseActivity
import com.gabriel.notebook.base.BaseFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getCurrentFragment(): BaseFragment? {
        return null
    }
}
