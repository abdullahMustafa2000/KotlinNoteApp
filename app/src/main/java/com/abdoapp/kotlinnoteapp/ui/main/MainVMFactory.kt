package com.abdoapp.kotlinnoteapp.ui.main

import android.app.Application
import androidx.lifecycle.ViewModelProvider

class MainVMFactory(application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {

    init {
        MainActivityViewModel(application)
    }
}