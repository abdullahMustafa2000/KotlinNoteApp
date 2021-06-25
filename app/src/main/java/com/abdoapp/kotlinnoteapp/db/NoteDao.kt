package com.abdoapp.kotlinnoteapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insertNote(itemNote: ItemNote)

    @Update
    fun updateNote(itemNote: ItemNote)

    @Query("DELETE FROM notes WHERE id= :ID")
    fun deleteNote(ID: Int)

    @Query("SELECT * FROM notes")
    fun getAllNotes() : LiveData<List<ItemNote>>
}