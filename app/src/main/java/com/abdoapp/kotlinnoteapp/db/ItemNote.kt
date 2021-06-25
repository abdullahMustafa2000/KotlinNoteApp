package com.abdoapp.kotlinnoteapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class ItemNote {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var title: String = ""
    var content: String = ""
}
