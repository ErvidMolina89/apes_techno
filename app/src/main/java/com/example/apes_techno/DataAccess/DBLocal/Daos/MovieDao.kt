package com.example.quenta.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.MovieAndAllImages
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.MovieAndAllImagesItem

@Dao
abstract class MovieDao : BaseDao<Movie> {
    @Query("SELECT * FROM Movie")
    abstract fun getAllMovie(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movie")
    abstract fun getMovie(): LiveData<List<MovieAndAllImages>>

    @Query("SELECT * FROM Movie")
    abstract fun getMovieAndImageItem(): LiveData<MovieAndAllImagesItem>

    @Query("SELECT * FROM Movie WHERE id = :id")
    abstract fun getMovieID(id: Int): LiveData<Movie>

    @Query("DELETE FROM Movie")
    abstract fun nukeMovie()
}