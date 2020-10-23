package com.example.quenta.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.ItemMovie

@Dao
abstract class ItemMovieDao : BaseDao<ItemMovie> {
    @Query("SELECT * FROM ItemMovie")
    abstract fun getItemMovies(): LiveData<List<ItemMovie>>

    @Query("SELECT * FROM ItemMovie WHERE id = :id")
    abstract fun getItemMovieID(id: Int): LiveData<List<ItemMovie>>

    @Query("DELETE FROM ItemMovie")
    abstract fun nukeItemMovie()
}