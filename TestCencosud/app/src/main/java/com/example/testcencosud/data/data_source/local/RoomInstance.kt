package com.example.testcencosud.data.data_source.local

import android.content.Context
import androidx.room.Room

object RoomInstance {

    fun getRoom(context: Context): MovieDB {
        return Room
            .databaseBuilder(context, MovieDB::class.java, "movie")
            .build()
    }
}