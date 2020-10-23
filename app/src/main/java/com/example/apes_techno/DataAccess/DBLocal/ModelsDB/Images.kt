package com.example.apes_techno.DataAccess.DBLocal.ModelsDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.apes_techno.Models.BaseModel

@Entity(foreignKeys = [
    ForeignKey(entity = Movie::class,
        parentColumns = ["Id"],
        childColumns = ["MovieID"])
])
data class Images (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")                var id                  : Int? = null,
    @ColumnInfo(name = "IconUrl")           var icon_url            : String? = null,
    @ColumnInfo(name = "MediumUrl")         var medium_url          : String? = null,
    @ColumnInfo(name = "ScreenUrl")         var screen_url          : String? = null,
    @ColumnInfo(name = "ScreenLargeUrl")    var screen_large_url    : String? = null,
    @ColumnInfo(name = "SmallUrl")          var small_url           : String? = null,
    @ColumnInfo(name = "SuperUrl")          var super_url           : String? = null,
    @ColumnInfo(name = "OriginalUrl")       var original_url        : String? = null,
    @ColumnInfo(name = "MovieID")           var movieID             : Int? = null,
    @ColumnInfo(name = "ImageTags")         var image_tags          : String? = null
): BaseModel()