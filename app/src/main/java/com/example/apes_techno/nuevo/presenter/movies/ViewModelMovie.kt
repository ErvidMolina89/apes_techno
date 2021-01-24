package com.example.apes_techno.nuevo.presenter.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apes_techno.nuevo.entities.NewMovie
import com.example.apes_techno.nuevo.use_case.movies.GetApiListMoviesUseCase

class ViewModelMovie (private val getApiListMoviesUseCase: GetApiListMoviesUseCase) : ViewModel() {

    var listMovies: MutableLiveData<MutableList<NewMovie>> = MutableLiveData()

    fun getListMovies(){
        listMovies.value = getApiListMoviesUseCase.invoke()
    }
}