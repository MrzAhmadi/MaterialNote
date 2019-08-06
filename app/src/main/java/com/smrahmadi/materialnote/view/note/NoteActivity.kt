package com.smrahmadi.materialnote.view.note

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.smrahmadi.materialnote.App.Companion.NOTE_KEY
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.helper.toast
import com.smrahmadi.materialnote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        initView()
        getNote()

    }

    private fun initView() {
        setContentView(R.layout.activity_note)
        title = null
    }

    private fun getNote() {
        if (intent.extras != null && intent.hasExtra(NOTE_KEY)) {
            note = intent.getParcelableExtra(NOTE_KEY)
            titleText.setText(note?.title)
            descriptionText.setText(note?.description)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> {
                if (titleText.text.isNotEmpty() || descriptionText.text.isNotEmpty()) {
                    if (note == null) {
                        viewModel.insert(
                            Note(
                                title = titleText.text.toString(),
                                description = descriptionText.text.toString()
                            )
                        )
                    } else {
                        note!!.title = titleText.text.toString()
                        note!!.description = descriptionText.text.toString()
                        viewModel.update(note!!)
                    }
                    finish()
                } else
                    toast(resources.getString(R.string.insert_your_text))
            }
        }
        return true
    }
}

