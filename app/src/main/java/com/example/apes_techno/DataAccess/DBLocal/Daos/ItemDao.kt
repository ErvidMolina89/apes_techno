package com.example.quenta.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Item

@Dao
abstract class ItemDao : BaseDao<Item> {
    @Query("SELECT * FROM Item")
    abstract fun getItems(): LiveData<List<Item>>

    @Query("SELECT * FROM Item WHERE id = :id")
    abstract fun getItemID(id: Int): LiveData<Item>

    @Query("DELETE FROM Item")
    abstract fun nukeItem()
}