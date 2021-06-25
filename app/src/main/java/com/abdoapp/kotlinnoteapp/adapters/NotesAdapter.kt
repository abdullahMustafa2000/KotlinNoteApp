package com.abdoapp.kotlinnoteapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.abdoapp.kotlinnoteapp.db.ItemNote
import com.abdoapp.kotlinnoteapp.R
import com.abdoapp.kotlinnoteapp.ui.addnote.AddNoteActivity

class NotesAdapter(context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var context: Context? = context
    var notesList: List<ItemNote>? = arrayListOf()
    var onDeleteBtnClick: OnDeleteBtnClick? = null

    fun setList(notesList: List<ItemNote>) {
        this.notesList = notesList
    }

    fun setOnDeleteClick(onDeleteBtnClick: OnDeleteBtnClick) {
        this.onDeleteBtnClick = onDeleteBtnClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val itemNote: ItemNote? = notesList?.get(position)
        holder.itemTitle.text = itemNote?.title
        holder.itemContent.text = itemNote?.content

        holder.editItem.setOnClickListener {
            startEditActivity(itemNote)
        }

        holder.deleteItem.setOnClickListener {
            onDeleteBtnClick?.onDelete(itemNote!!.id)
        }
    }

    private fun startEditActivity(itemNote: ItemNote?) {
        val intent = Intent(context, AddNoteActivity::class.java)
        intent.putExtra("title", itemNote?.title)
        intent.putExtra("content", itemNote?.content)
        intent.putExtra("id", itemNote?.id)
        context?.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return notesList!!.size
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.item_note_title)
        val itemContent: TextView = itemView.findViewById(R.id.item_note_content)
        val deleteItem: Button = itemView.findViewById(R.id.delete_note_btn)
        val editItem: Button = itemView.findViewById(R.id.edit_note_btn)
    }

    interface OnDeleteBtnClick {
        fun onDelete(itemId: Int)
    }
}