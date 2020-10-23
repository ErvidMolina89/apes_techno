package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces

import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movie

interface IFragmentHomeMoviesView {
    fun failureService(response: MessageResponse)
    fun responseHomeMovies(movies: MutableList<Movie>)
}