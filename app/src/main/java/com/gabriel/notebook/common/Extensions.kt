package com.gabriel.notebook.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setActionBarTitle(title: String) {
    requireActivity().let {
        if (it is AppCompatActivity) {
            it.supportActionBar?.title = title
        } else {
            it.actionBar?.title = title
        }
    }
}

fun Fragment.showBackButton(){
    requireActivity().let {
        if (it is AppCompatActivity) {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            it.actionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}

fun Fragment.hideBackButton(){
    requireActivity().let {
        if (it is AppCompatActivity) {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            it.actionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}