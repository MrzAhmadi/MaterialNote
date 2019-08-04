package com.smrahmadi.materialnote.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NoteListItemDecoration : RecyclerView.ItemDecoration() {

    private var position = -1
    private var itemCount = -1

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        itemCount = state.itemCount
        position = parent.getChildAdapterPosition(view)

        if (position == RecyclerView.NO_POSITION)
            return

        if (position == 0)
            outRect.set(8, 8, 8, 8);

        else if (itemCount > 0 && position == itemCount - 1)
            outRect.set(8, 8, 8, 8);

        else
            outRect.set(8, 8, 8, 0);
    }
}