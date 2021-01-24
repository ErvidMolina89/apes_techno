package com.example.apes_techno.nuevo.data_access.repositories

import com.example.apes_techno.nuevo.entities.NewMovie
import com.example.apes_techno.nuevo.entities.NewMovies
import com.example.apes_techno.nuevo.utils.OriginData

interface apiMovies {
    fun getMovies(): MutableList<NewMovie>
}

interface dbMovies {
    fun getMovies(): MutableList<NewMovie>
}

interface preferenceMovies {
    fun getMovies(): MutableList<NewMovie>
}

class RepoMovies (
    private val apiMovies: apiMovies,
    private val dbMovies: dbMovies,
    private val preferenceMovies: preferenceMovies
) {
    fun returnListMovie(originData: OriginData) :MutableList<NewMovie>{
        return when (originData){
            OriginData.api -> {
                apiMovies.getMovies()
            }
            OriginData.db -> {
                dbMovies.getMovies()
            }
            OriginData.preference -> {
                preferenceMovies.getMovies()
            }
        }
    }
}