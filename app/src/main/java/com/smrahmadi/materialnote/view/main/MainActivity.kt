package com.smrahmadi.materialnote.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.utils.NoteListItemDecoration
import com.smrahmadi.materialnote.view.main.adapter.NoteListAdapter
import com.smrahmadi.materialnote.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var liveData: NoteViewModel
    private lateinit var adapter: NoteListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveData = ViewModelProviders.of(this).get(NoteViewModel::class.java)
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
        })
//        setTestData()
    }


    private fun setTestData(){
        var noteListTempTest: MutableList<Note> = mutableListOf()
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        noteListTempTest.add(Note(1,"What is Lorem Ipsum?","is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "))
        adapter.setList(noteListTempTest)
    }
}
