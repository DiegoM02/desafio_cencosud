package com.example.testcencosud.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testcencosud.domain.uses_case.GetMovie
import com.example.testcencosud.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getMovie: GetMovie
) : ViewModel() {

    fun getMovie(id: String) = liveData(Dispatchers.IO) {
        emit(Response.Loading)

        try{
            getMovie.invoke(id).collect {
                emit(it)
            }

        }catch (e: Exception){
            emit(Response.Failure(e))
            e.message?.let { Log.e("ERROR:", it) }
        }
    }
}