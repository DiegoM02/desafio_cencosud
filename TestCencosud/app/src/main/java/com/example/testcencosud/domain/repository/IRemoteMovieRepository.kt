package com.example.testcencosud.domain.repository

import com.example.testcencosud.domain.model.Movie
import com.example.testcencosud.utils.Response
import kotlinx.coroutines.flow.Flow

interface IRemoteMovieRepository  {

    fun getAllMovie(): Flow<Response<List<Movie>>>

    fun getMovie(): Flow<Response<Movie>>

}