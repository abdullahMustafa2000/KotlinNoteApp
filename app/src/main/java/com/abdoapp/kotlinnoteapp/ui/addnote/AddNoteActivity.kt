package com.abdoapp.kotlinnoteapp.ui.addnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.abdoapp.kotlinnoteapp.R
import com.abdoapp.kotlinnoteapp.db.ItemNote
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddNoteActivity : AppCompatActivity() {

    private var id: Int? = -1
    private var fabAddNote: FloatingActionButton? = null
    private var fabCancel: FloatingActionButton? = null
    private var titleNoteET: EditText? = null
    private var contentNoteET: EditText? = null

    private var noteViewModel: AddNoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        initView()
        initViewModel()
        initComingNoteData()
        fabAddNote?.setOnClickListener {
            if (id!! > 0)
                updateNote()
            else
                addNoteToDb()
        }

        fabCancel?.setOnClickListener { finish() }
    }

    private fun updateNote() {
        val note = ItemNote()
        note.title = titleNoteET?.text.toString()
        note.content = contentNoteET?.text.toString()
        note.id = this.id!!
        noteViewModel?.updateNote(note)
        finish()
    }

    private fun initComingNoteData() {
        val intent = intent
        val title: String? = intent.getStringExtra("title")
        val content: String? = intent.getStringExtra("content")
        id = intent.getIntExtra("id", -1)
        if (intent != null)
            setNoteData(title, content)
    }

    private fun setNoteData(title: String?, content: String?) {
        titleNoteET?.setText(title)
        contentNoteET?.setText(content)
    }

    private fun addNoteToDb() {
        if (!titleNoteET?.text.isNullOrEmpty() && !contentNoteET?.text.isNullOrEmpty()) {
            val note = ItemNote()
            note.content = contentNoteET?.text.toString()
            note.title = titleNoteET?.text.toString()
            noteViewModel?.addNote(note)
            finish()
        }
    }

    private fun initViewModel() {
        val factory = NoteVMFactory(application)
        noteViewModel = ViewModelProvider(this, factory).get(AddNoteViewModel::class.java)
    }

    private fun initView() {
        fabAddNote = findViewById(R.id.fab_add)
        fabCancel = findViewById(R.id.fab_cancel)
        titleNoteET = findViewById(R.id.title_et)
        contentNoteET = findViewById(R.id.content_et)
    }
}