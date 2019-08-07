package com.smrahmadi.materialnote

import android.app.Application

class App : Application() {
    companion object {
        const val NOTE_KEY = "note"
        const val OPEN_OPTION = 0
        const val DELETE_OPTION = 1
    }
}