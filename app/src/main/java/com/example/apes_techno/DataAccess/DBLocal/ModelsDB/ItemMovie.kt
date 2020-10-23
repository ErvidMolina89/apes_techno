package com.example.apes_techno.DataAccess.DBLocal.ModelsDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.apes_techno.Models.BaseModel

@Entity(foreignKeys = [
    ForeignKey(entity = Movie::class,
        parentColumns = ["Id"],
        childColumns = ["MovieID"]),
    ForeignKey(entity = Item::class,
        parentColumns = ["Id"],
        childColumns = ["ItemID"])
])
data class ItemMovie (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")            var id              : Int? = null,
    @ColumnInfo(name = "MovieID")       var movieID         : Int? = null,
    @ColumnInfo(name = "ItemID")        var itemID          : Int? = null
): BaseModel()