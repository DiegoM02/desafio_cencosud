package com.example.testcencosud.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testcencosud.data.model.DetailMovie
import com.example.testcencosud.data.model.Item

@Dao
interface MovieDao {
    @Query("SELECT * FROM DetailMovie")
    fun getAll(): List<DetailMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DetailMovie>)

    @Query("SELECT * FROM DetailMovie WHERE id = :id")
    fun getMovie(id:String): DetailMovie
}