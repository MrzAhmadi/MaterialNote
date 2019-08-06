package com.smrahmadi.materialnote.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smrahmadi.materialnote.R
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.view.main.call.NoteListCallback
import com.smrahmadi.materialnote.view.main.viewholder.NoteItemViewHolder

class NoteListAdapter(private var callback: NoteListCallback) : RecyclerView.Adapter<NoteItemViewHolder>() {

    private var list: List<Note> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_note, parent, false)
        return NoteItemViewHolder(itemView, callback)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Note>) {
        this.list = list
        notifyDataSetChanged()
    }

}