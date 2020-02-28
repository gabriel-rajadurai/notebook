package com.gabriel.notebook.common

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorMsg")
fun TextInputLayout.errorMsg(errorMsg: String?) {
    error = errorMsg
}