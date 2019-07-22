package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.R

fun Activity.hideKeyboard() {
    currentFocus?.let { v ->
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also { inputMethodManager ->
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}