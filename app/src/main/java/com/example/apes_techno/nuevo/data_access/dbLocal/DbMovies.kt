package com.example.apes_techno.nuevo.data_access.dbLocal

import com.example.apes_techno.nuevo.data_access.repositories.dbMovies
import com.example.apes_techno.nuevo.entities.NewMovie

class DbMovies: dbMovies {
    override fun getMovies(): MutableList<NewMovie> {
        return emptyList<NewMovie>().toMutableList()
    }
}