package com.smrahmadi.materialnote.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.utils.NoteListItemDecoration
import com.smrahmadi.materialnote.view.main.adapter.NoteListAdapter
import com.smrahmadi.materialnote.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var liveData: MainViewModel
    private lateinit var adapter: NoteListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveData = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initView()
        initList()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        noteList.addItemDecoration(NoteListItemDecoration(resources.getDimension(R.dimen.margin_8dp).toInt()))
    }

    private fun initList() {
        adapter = NoteListAdapter()
        noteList.adapter = adapter
        liveData.allNotes.observe(this, Observer {
            adapter.setList(it)
        }) }

}
