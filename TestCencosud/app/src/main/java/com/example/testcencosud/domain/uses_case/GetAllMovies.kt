package com.example.testcencosud.domain.uses_case

import com.example.testcencosud.domain.model.Movie
import com.example.testcencosud.domain.repository.IRemoteMovieRepository
import com.example.testcencosud.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMovies @Inject constructor(private val repository: IRemoteMovieRepository) {

    fun invoke() : Flow<Response<List<Movie>>> = repository.getAllMovie()
}