package com.abdoapp.kotlinnoteapp.ui.main

import androidx.lifecycle.LiveData
import com.abdoapp.kotlinnoteapp.db.ItemNote
import com.abdoapp.kotlinnoteapp.db.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityRepository(private val noteDao: NoteDao) {


    fun retrieveAllNotes() : LiveData<List<ItemNote>> {
        return noteDao.getAllNotes()
    }

    fun deleteNote(itemId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteNote(itemId)
        }
    }
}