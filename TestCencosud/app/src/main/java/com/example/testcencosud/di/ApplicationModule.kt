package com.example.testcencosud.di

import android.content.Context
import com.example.testcencosud.data.data_source.local.MovieDB
import com.example.testcencosud.data.data_source.local.RoomInstance
import com.example.testcencosud.data.data_source.remote.ApiService
import com.example.testcencosud.data.data_source.remote.RetrofitInstance
import com.example.testcencosud.data.repository.LocalMovieRepository
import com.example.testcencosud.data.repository.RemoteMovieRepository
import com.example.testcencosud.domain.repository.ILocalMovieRepository
import com.example.testcencosud.domain.repository.IRemoteMovieRepository
import com.example.testcencosud.domain.uses_case.GetAllMovies
import com.example.testcencosud.domain.uses_case.GetMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MovieDB =
        RoomInstance.getRoom(context)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitInstance.getInstance()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesMovieRepository(
        apiService: ApiService,
        room: MovieDB
    ): IRemoteMovieRepository = RemoteMovieRepository(apiService, room)

    @Provides
    @Singleton
    fun providesLocalMovieRepository(
        room: MovieDB
    ): ILocalMovieRepository = LocalMovieRepository(room)

    @Provides
    @Singleton
    fun providesGetAllMovie(movieRepository: IRemoteMovieRepository): GetAllMovies =
        GetAllMovies(movieRepository)

    @Provides
    @Singleton
    fun providesGetMovie(movieRepository: ILocalMovieRepository): GetMovie =
        GetMovie(movieRepository)

}