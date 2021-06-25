package com.abdoapp.kotlinnoteapp.ui.addnote

import com.abdoapp.kotlinnoteapp.db.ItemNote
import com.abdoapp.kotlinnoteapp.db.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteRepository(private val noteDao: NoteDao) {

    fun addNoteToDb(note: ItemNote) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insertNote(note)
        }
    }

    fun updateNote(note: ItemNote) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.updateNote(note)
        }
    }
}