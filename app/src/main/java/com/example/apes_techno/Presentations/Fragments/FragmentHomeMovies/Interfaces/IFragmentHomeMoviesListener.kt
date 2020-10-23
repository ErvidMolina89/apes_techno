package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces

import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie
import com.example.apes_techno.Models.MessageResponse

interface IFragmentHomeMoviesListener {
    fun failureService(response: MessageResponse)
    fun responseHomeMovies(movies: MutableList<Movie>)
}