package com.abdoapp.kotlinnoteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemNote::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun notesDao() : NoteDao

    companion object {
        private var INSTACE: MyDatabase? = null

        public fun getDatabase(context: Context) : MyDatabase {
            return INSTACE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "word_database"
                ).build()
                INSTACE = instance
                // return instance
                instance
            }


        }
    }


}