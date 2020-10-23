package com.example.quenta.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Images

@Dao
abstract class ImagesDao : BaseDao<Images> {
    @Query("SELECT * FROM Images")
    abstract fun getImages(): LiveData<List<Images>>

    @Query("SELECT * FROM Images WHERE movieID = :id")
    abstract fun getImagesID(id: Int): LiveData<Images>

    @Query("DELETE FROM Images")
    abstract fun nukeImages()
}