package com.example.testcencosud.domain.repository

import com.example.testcencosud.data.model.DetailMovie
import com.example.testcencosud.data.model.Item
import kotlinx.coroutines.flow.Flow
import com.example.testcencosud.utils.Response

interface ILocalMovieRepository {
    fun getLocalAllMovies(): Flow<Response<List<DetailMovie>>>
    fun getLocalMovie(id: String): Flow<Response<DetailMovie>>
}