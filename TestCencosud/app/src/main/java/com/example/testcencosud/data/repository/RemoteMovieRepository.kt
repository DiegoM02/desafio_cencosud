package com.example.testcencosud.data.repository

import android.util.Log
import com.example.testcencosud.data.data_source.local.MovieDB
import com.example.testcencosud.data.data_source.remote.ApiService
import com.example.testcencosud.data.model.MapperDataModel
import com.example.testcencosud.data.model.MapperRoomModel
import com.example.testcencosud.domain.model.Movie
import com.example.testcencosud.domain.repository.IRemoteMovieRepository
import com.example.testcencosud.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception
import javax.inject.Inject


class RemoteMovieRepository @Inject constructor(
    private val db: ApiService,
    private val room: MovieDB
) : IRemoteMovieRepository {
    override fun getAllMovie(): Flow<Response<List<Movie>>> = callbackFlow {
        val result = db.getAllMovies()
        if (result.isSuccessful) {
            val resolve = try {
                val data = result.body()
                val itemsMovie = data?.let { movies ->
                    MapperDataModel.fromDataModelList(movies.items)
                }
                room.movieDao().insertAll(MapperRoomModel.fromDataModel(data!!.items))
                Response.Success(itemsMovie)

            } catch (e: Exception) {
                Response.Failure(e)
            }
            trySend(resolve as Response<List<Movie>>).isSuccess
        }

        awaitClose {
            this.cancel()
        }
    }

    override fun getMovie(): Flow<Response<Movie>> {
        TODO("Not yet implemented")
    }
}