package com.smrahmadi.materialnote.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smrahmadi.materialnote.App.Companion.DELETE_OPTION
import com.smrahmadi.materialnote.App.Companion.NOTE_KEY
import com.smrahmadi.materialnote.App.Companion.OPEN_OPTION
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.dagger.component.DaggerAppComponent
import com.smrahmadi.materialnote.dagger.module.AppModule
import com.smrahmadi.materialnote.dagger.module.RoomModule
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.data.repository.NoteRepository
import com.smrahmadi.materialnote.utils.NoteListItemDecoration
import com.smrahmadi.materialnote.view.main.adapter.NoteListAdapter
import com.smrahmadi.materialnote.view.main.call.NoteListCallback
import com.smrahmadi.materialnote.view.note.NoteActivity
import com.smrahmadi.materialnote.viewmodel.DatabaseViewModel
import com.smrahmadi.materialnote.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NoteListCallback {


    @Inject
    lateinit var noteRepository: NoteRepository

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteListAdapter
    private var deleteAll: MenuItem? = null

    private var isNotEmptyList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .roomModule(RoomModule(application))
            .build()
            .inject(this)

        viewModel = ViewModelProviders
            .of(this, DatabaseViewModel(noteRepository))
            .get(MainViewModel::class.java)

        initList()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        noteList.addItemDecoration(NoteListItemDecoration(resources.getDimension(R.dimen.margin_8dp).toInt()))
        addNote.setOnClickListener {
            startNoteActivity(null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        deleteAll = menu!!.findItem(R.id.deleteAll)
        if (!isNotEmptyList)
            deleteAll?.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll -> {
                alert(getString(R.string.do_you_want_delete_all_items), null) {
                    yesButton {
                        viewModel.deleteAll()
                    }
                    noButton {}
                }.show()
            }
        }
        return true
    }

    private fun initList() {
        adapter = NoteListAdapter(this)
        noteList.adapter = adapter
        viewModel.allNotes.observe(this, Observer {
            adapter.setList(it)
            deleteAll?.isVisible = it.isNotEmpty()
            isNotEmptyList = it.isNotEmpty()
        })
    }

    private fun startNoteActivity(note: Note?) {
        startActivity<NoteActivity>(
            NOTE_KEY to note
        )
    }

    override fun onItemClick(note: Note) = startNoteActivity(note)

    override fun onItemLongClick(note: Note) {
        val options = listOf(getString(R.string.open), getString(R.string.delete))
        selector(getString(R.string.choose_your_action), options) { _, i ->
            when (i) {
                OPEN_OPTION -> startNoteActivity(note)
                DELETE_OPTION -> {
                    alert(getString(R.string.do_you_want_delete_this_item), null) {
                        yesButton {
                            viewModel.delete(note)
                        }
                        noButton {}
                    }.show()
                }
            }
        }
    }

}
