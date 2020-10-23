package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces

import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movie

interface IFragmentDetailsMovieListener {
    fun failureService(response: MessageResponse)
    fun responseDetailMovies(movies: MutableList<Movie>)
}