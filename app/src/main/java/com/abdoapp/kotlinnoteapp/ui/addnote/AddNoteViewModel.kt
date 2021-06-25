package com.abdoapp.kotlinnoteapp.ui.addnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.abdoapp.kotlinnoteapp.db.ItemNote
import com.abdoapp.kotlinnoteapp.db.MyDatabase
import com.abdoapp.kotlinnoteapp.db.NoteDao

class AddNoteViewModel(application: Application): AndroidViewModel(application) {

    private var addNoteRepository: AddNoteRepository? = null
    init {
        val noteDao: NoteDao = MyDatabase.getDatabase(application).notesDao()
        addNoteRepository = AddNoteRepository(noteDao)
    }

    fun addNote(note: ItemNote) {
        addNoteRepository?.addNoteToDb(note)
    }

    fun updateNote(note: ItemNote) {
        addNoteRepository?.updateNote(note)
    }
}