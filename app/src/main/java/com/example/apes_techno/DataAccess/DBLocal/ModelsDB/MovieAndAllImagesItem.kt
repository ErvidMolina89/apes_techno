package com.example.apes_techno.DataAccess.DBLocal.ModelsDB

import androidx.room.Embedded
import androidx.room.Relation


class MovieAndAllImagesItem {

    @Embedded
    var movie: Movie? = null

    @Relation(parentColumn = "Id",
        entityColumn = "MovieID")
    var images: Images = Images()

    @Relation(parentColumn = "Id",
        entityColumn = "MovieID")
    var listItemMovie: List<ItemMovie> = ArrayList()
}