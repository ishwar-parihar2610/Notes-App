package com.ishwar.notesapp.util

import android.content.Context
import android.widget.Toast

class Constant {
    val tag = "Activity"

    companion object {
        fun showToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}