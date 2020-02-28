package com.gabriel.notebook.common

import android.app.Application
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.setActionBarTitle(title: String) {
    requireActivity().let {
        if (it is AppCompatActivity) {
            it.supportActionBar?.title = title
        } else {
            it.actionBar?.title = title
        }
    }
}

fun Fragment.showBackButton() {
    requireActivity().let {
        if (it is AppCompatActivity) {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            it.actionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}

fun Fragment.hideBackButton() {
    requireActivity().let {
        if (it is AppCompatActivity) {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            it.actionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}

fun Date.toFormat(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(this)
}

fun AndroidViewModel.getString(@StringRes resId: Int) =
    getApplication<Application>().getString(resId)

fun View.showSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}