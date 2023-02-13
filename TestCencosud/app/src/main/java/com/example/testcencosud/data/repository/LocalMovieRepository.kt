package com.example.testcencosud.data.repository

import com.example.testcencosud.data.data_source.local.MovieDB
import com.example.testcencosud.data.model.DetailMovie
import com.example.testcencosud.data.model.Item
import com.example.testcencosud.domain.repository.ILocalMovieRepository
import com.example.testcencosud.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

import java.lang.Exception
import javax.inject.Inject

class LocalMovieRepository @Inject constructor(
    private val room: MovieDB
) : ILocalMovieRepository {
    override fun getLocalAllMovies(): Flow<Response<List<DetailMovie>>> = callbackFlow {
        val resolve = try {
            Response.Success(room.movieDao().getAll())
        } catch (e: Exception) {
            Response.Failure(e)
        }
        trySend(resolve as Response<List<DetailMovie>>).isSuccess

        awaitClose {
            this.cancel()
        }
    }


    override fun getLocalMovie(id: String): Flow<Response<DetailMovie>> = callbackFlow {
        val resolve = try {
            Response.Success(room.movieDao().getMovie(id))
        } catch (e: Exception) {
            Response.Failure(e)
        }
        trySend(resolve as Response<DetailMovie>).isSuccess

        awaitClose {
            this.cancel()
        }
    }
}