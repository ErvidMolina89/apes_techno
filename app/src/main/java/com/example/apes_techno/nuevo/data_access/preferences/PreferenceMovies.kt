package com.example.apes_techno.nuevo.data_access.preferences

import com.example.apes_techno.nuevo.data_access.repositories.preferenceMovies
import com.example.apes_techno.nuevo.entities.NewMovie
import com.example.apes_techno.nuevo.entities.NewMovies

class PreferenceMovies: preferenceMovies {
    override fun getMovies(): MutableList<NewMovie> {
        return emptyList<NewMovie>().toMutableList()
    }
}