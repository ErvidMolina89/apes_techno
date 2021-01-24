package com.example.apes_techno.nuevo.data_access.api

import com.example.apes_techno.nuevo.data_access.repositories.RepoMovies
import com.example.apes_techno.nuevo.data_access.repositories.apiMovies
import com.example.apes_techno.nuevo.entities.NewMovie
import com.example.apes_techno.nuevo.entities.NewMovies

class ApiMovies: apiMovies {
    override fun getMovies(): MutableList<NewMovie> {
        return emptyList<NewMovie>().toMutableList()
    }
}