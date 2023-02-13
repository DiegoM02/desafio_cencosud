package com.example.testcencosud.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testcencosud.data.model.DetailMovie

@Database(
    entities = [DetailMovie::class],
    version = 1,
)

abstract class MovieDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}