package com.hackheroes.healthybody.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.displayToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.displayToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}