package com.example.testcencosud.data.data_source.remote

import com.example.testcencosud.data.model.Items
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Accept: application/json")
    @GET("movies.json?key=cb03b960")
    suspend fun getAllMovies(): Response<Items>
}