package com.example.testcencosud.domain.model

import java.io.Serializable

data class Movie(
    val id: String,
    val title: String,
    val urlImage: String,
    val dateRelease: String
) : Serializable