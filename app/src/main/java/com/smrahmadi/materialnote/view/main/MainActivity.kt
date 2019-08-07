package com.smrahmadi.materialnote.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smrahmadi.materialnote.App.Companion.NOTE_KEY
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.utils.NoteListItemDecoration
import com.smrahmadi.materialnote.view.main.adapter.NoteListAdapter
import com.smrahmadi.materialnote.view.main.call.NoteListCallback
import com.smrahmadi.materialnote.view.note.NoteActivity
import com.smrahmadi.materialnote.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteListCallback {


    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initView()
        initList()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        noteList.addItemDecoration(NoteListItemDecoration(resources.getDimension(R.dimen.margin_8dp).toInt()))
        addNote.setOnClickListener {
            startNoteActivity(null)
        }
    }

    private fun initList() {
        adapter = NoteListAdapter(this)
        noteList.adapter = adapter
        viewModel.allNotes.observe(this, Observer {
            adapter.setList(it)
        })
    }

    private fun startNoteActivity(note: Note?) {
        val intent = Intent(this, NoteActivity::class.java)
        if (note != null)
            intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

    override fun onItemClick(note: Note) {
        startNoteActivity(note)
    }

    override fun onItemLongClick(note: Note) {

    }


}
