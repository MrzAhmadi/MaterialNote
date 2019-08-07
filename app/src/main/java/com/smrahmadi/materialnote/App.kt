package com.smrahmadi.materialnote

import android.app.Application
import android.content.Context

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null


        const val NOTE_KEY = "note"
        const val OPEN_XOPTION = 0
        const val DELETE_OPTION = 1

        fun getContext(): Context {
            return instance!!.applicationContext
        }
    }

}