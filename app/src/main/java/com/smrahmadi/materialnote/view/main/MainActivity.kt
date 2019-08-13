package com.smrahmadi.materialnote.view.main

import android.content.DialogInterface
import android.content.Intent
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
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.helper.ItemOptionsCallback
import com.smrahmadi.materialnote.helper.shoItemOptionsDialog
import com.smrahmadi.materialnote.helper.showDeleteAllItemsDialog
import com.smrahmadi.materialnote.helper.showDeleteItemDialog
import com.smrahmadi.materialnote.utils.NoteListItemDecoration
import com.smrahmadi.materialnote.view.main.adapter.NoteListAdapter
import com.smrahmadi.materialnote.view.main.call.NoteListCallback
import com.smrahmadi.materialnote.view.note.NoteActivity
import com.smrahmadi.materialnote.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteListCallback {


    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteListAdapter
    private var deleteAll: MenuItem? = null

    private var isNotEmptyList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
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
                showDeleteAllItemsDialog(DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                    viewModel.deleteAll()
                })
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
        val intent = Intent(this, NoteActivity::class.java)
        if (note != null)
            intent.putExtra(NOTE_KEY, note)
        startActivity(intent)
    }

    override fun onItemClick(note: Note) {
        startNoteActivity(note)
    }

    override fun onItemLongClick(note: Note) {
        shoItemOptionsDialog(object : ItemOptionsCallback {
            override fun onDialogOptionClick(item: Int) {
                when (item) {
                    OPEN_OPTION -> startNoteActivity(note)
                    DELETE_OPTION -> {
                        showDeleteItemDialog(DialogInterface.OnClickListener { dialog, _ ->
                            dialog.dismiss()
                            viewModel.delete(note)
                        })
                    }
                }
            }
        })
    }

}
