package com.example.testcencosud.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testcencosud.domain.uses_case.GetAllMovies
import com.example.testcencosud.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getAllMovies: GetAllMovies

    ) : ViewModel() {

    fun getAllMovies()  = liveData(Dispatchers.IO) {
        emit(Response.Loading)

        try{
            getAllMovies.invoke().collect {
                emit(it)
            }

        }catch (e: Exception){
            emit(Response.Failure(e))
            e.message?.let { Log.e("ERROR:", it) }
        }
    }
}