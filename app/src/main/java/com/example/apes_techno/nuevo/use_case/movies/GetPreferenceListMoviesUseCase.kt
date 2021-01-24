package com.example.apes_techno.nuevo.use_case.movies

import com.example.apes_techno.nuevo.data_access.repositories.RepoMovies
import com.example.apes_techno.nuevo.entities.NewMovie
import com.example.apes_techno.nuevo.entities.NewMovies
import com.example.apes_techno.nuevo.utils.OriginData

class GetPreferenceListMoviesUseCase(val repoMovies: RepoMovies) {
    fun invoke (): MutableList<NewMovie> {
        return repoMovies.returnListMovie(OriginData.preference)
    }
}