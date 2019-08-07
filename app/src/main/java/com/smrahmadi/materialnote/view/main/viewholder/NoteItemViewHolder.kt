package com.smrahmadi.materialnote.view.main.viewholder

import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.smrahmadi.materialnote.data.model.Note
import com.smrahmadi.materialnote.view.main.call.NoteListCallback
import kotlinx.android.synthetic.main.item_note.view.*

class NoteItemViewHolder(itemView: View, private var callback: NoteListCallback) : RecyclerView.ViewHolder(itemView) {

    fun bind(note: Note) {
        if (TextUtils.isEmpty(note.title))
            itemView.title.visibility = GONE
        else {
            itemView.title.text = note.title
            itemView.title.visibility = VISIBLE
        }

        if (TextUtils.isEmpty(note.description))
            itemView.description.visibility = GONE
        else {
            itemView.description.text = note.description
            itemView.description.visibility = VISIBLE
        }

        itemView.setOnClickListener {
            callback.onItemClick(note)
        }

        itemView.setOnLongClickListener {
            callback.onItemLongClick(note)
            true
        }

    }
}