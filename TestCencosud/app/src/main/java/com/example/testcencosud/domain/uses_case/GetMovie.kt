package com.example.testcencosud.domain.uses_case

import com.example.testcencosud.data.model.DetailMovie
import com.example.testcencosud.domain.repository.ILocalMovieRepository
import com.example.testcencosud.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val repository: ILocalMovieRepository
){
    fun invoke(id:String): Flow<Response<DetailMovie>> = repository.getLocalMovie(id)

}