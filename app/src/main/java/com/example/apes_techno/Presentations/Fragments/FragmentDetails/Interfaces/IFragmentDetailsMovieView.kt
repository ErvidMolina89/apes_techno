package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces

import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie

interface IFragmentDetailsMovieView {
    fun failureService(response: MessageResponse)
    fun responseDetailMovies(movies: MutableList<Movie>)
}