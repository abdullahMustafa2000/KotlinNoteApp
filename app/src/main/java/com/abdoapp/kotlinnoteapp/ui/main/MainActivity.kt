package com.abdoapp.kotlinnoteapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdoapp.kotlinnoteapp.R
import com.abdoapp.kotlinnoteapp.adapters.NotesAdapter
import com.abdoapp.kotlinnoteapp.ui.addnote.AddNoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NotesAdapter.OnDeleteBtnClick {

    private var notesListRv:RecyclerView? = null
    private var fab: FloatingActionButton? = null

    var mViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        fab?.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
        initViewModel()
        initRV()
    }

    private fun initViewModel() {
        val factory = MainVMFactory(application)
        mViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }

    private fun initRV() {
        val adapterNotes = NotesAdapter(this)
        notesListRv?.layoutManager = LinearLayoutManager(this)
        mViewModel?.getAllNotes()?.observe(this, { list->
            run {
                adapterNotes.setList(list)
                adapterNotes.notifyDataSetChanged()
                notesListRv?.adapter = adapterNotes
            }
        })
        adapterNotes.setOnDeleteClick(this)
    }

    private fun initView() {
        notesListRv = findViewById(R.id.notes_rv)
        fab = findViewById(R.id.fab)
    }

    override fun onDelete(itemId: Int) {
        Log.d("TAG", "onDelete abdo : itemId= $itemId")
        mViewModel?.deleteNote(itemId)
    }
}