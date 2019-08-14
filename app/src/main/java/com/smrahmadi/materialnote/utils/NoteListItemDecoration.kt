package com.smrahmadi.materialnote.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NoteListItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    private var position = -1
    private var itemCount = -1

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        itemCount = state.itemCount
        position = parent.getChildAdapterPosition(view)

        if (position == RecyclerView.NO_POSITION)
            return

        if (position == 0)
            outRect.set(margin, margin*2, margin, 0)
        else if (itemCount > 0 && position == itemCount - 1)
            outRect.set(margin, margin, margin, margin*2)
        else
            outRect.set(margin, margin, margin, 0)
    }
}