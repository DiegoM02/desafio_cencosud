package com.example.testcencosud.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailMovie(
    @PrimaryKey val id: String,
    val contentRating: String,
    val directors: String,
    val genres: String,
    val imDbRating: String,
    val image: String,
    val plot: String,
    val releaseState: String,
    val runtimeMins: String,
    val stars: String,
    val title: String,
    val year: String
)
