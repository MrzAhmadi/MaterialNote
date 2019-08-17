package com.smrahmadi.materialnote.helper

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.smrahmadi.materialnote.R

@Deprecated("showToast")
fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

@Deprecated("showDeleteItemDialog")
fun Context.showDeleteItemDialog(onPositiveClickListener: DialogInterface.OnClickListener) {
    val alert = AlertDialog.Builder(this)
    alert.setTitle(getString(R.string.do_you_want_delete_this_item))
    alert.setNegativeButton(resources.getString(R.string.no), null)
    alert.setPositiveButton(resources.getString(R.string.yes), onPositiveClickListener)
    alert.show()
}

@Deprecated("showDeleteAllItemsDialog")
fun Context.showDeleteAllItemsDialog(onPositiveClickListener: DialogInterface.OnClickListener) {
    val alert = AlertDialog.Builder(this)
    alert.setTitle(getString(R.string.do_you_want_delete_all_items))
    alert.setNegativeButton(resources.getString(R.string.no), null)
    alert.setPositiveButton(resources.getString(R.string.yes), onPositiveClickListener)
    alert.show()
}

@Deprecated("shoItemOptionsDialog")
fun Context.shoItemOptionsDialog(callback: ItemOptionsCallback) {
    val options = arrayOf(
        resources.getString(R.string.open),
        resources.getString(R.string.delete)
    )
    val alert = AlertDialog.Builder(this)
    alert.setTitle(resources.getString(R.string.choose_your_action))
    alert.setItems(options) { _, which ->
        callback.onDialogOptionClick(which)
    }
    alert.show()
}

interface ItemOptionsCallback {
    fun onDialogOptionClick(item: Int)
}