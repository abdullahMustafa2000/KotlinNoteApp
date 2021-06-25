package com.abdoapp.kotlinnoteapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.abdoapp.kotlinnoteapp.db.ItemNote
import com.abdoapp.kotlinnoteapp.db.MyDatabase

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private var repository: MainActivityRepository? = null
    init {
        val noteDao = MyDatabase.getDatabase(application).notesDao()
        repository = MainActivityRepository(noteDao)
    }

    fun getAllNotes() : LiveData<List<ItemNote>> {
        return repository!!.retrieveAllNotes()
    }

    fun deleteNote(itemId: Int) {
        repository?.deleteNote(itemId)
    }
}