package com.abdoapp.kotlinnoteapp.ui.addnote

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class NoteVMFactory(application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
    init {
        AddNoteViewModel(application)
    }
}