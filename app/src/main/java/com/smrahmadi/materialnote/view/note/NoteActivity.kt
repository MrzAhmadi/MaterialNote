package com.smrahmadi.materialnote.view.note

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.smrahmadi.materialnote.App.Companion.NOTE_KEY
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.dagger.component.DaggerAppComponent
import com.smrahmadi.materialnote.dagger.module.AppModule
import com.smrahmadi.materialnote.dagger.module.RoomModule
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.data.repository.NoteRepository
import com.smrahmadi.materialnote.helper.showDeleteItemDialog
import com.smrahmadi.materialnote.viewmodel.DatabaseViewModel
import com.smrahmadi.materialnote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class NoteActivity : AppCompatActivity() {

    @Inject
    lateinit var noteRepository: NoteRepository

    private lateinit var viewModel: NoteViewModel
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = intent.getParcelableExtra(NOTE_KEY)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .roomModule(RoomModule(application))
            .build()
            .inject(this)

        viewModel = ViewModelProviders
            .of(this, DatabaseViewModel(noteRepository))
            .get(NoteViewModel::class.java)

        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_note)
        title = null
        if (note != null) {
            titleText.setText(note?.title)
            descriptionText.setText(note?.description)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note, menu)
        val deleteItem = menu!!.findItem(R.id.delete)
        deleteItem.isVisible = note != null
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
                    toast(R.string.insert_your_text)
            }

            R.id.delete -> {
                showDeleteItemDialog(DialogInterface.OnClickListener { dialog, _ ->
                    viewModel.delete(note!!)
                    dialog.dismiss()
                    finish()
                })
            }
        }
        return true
    }
}

