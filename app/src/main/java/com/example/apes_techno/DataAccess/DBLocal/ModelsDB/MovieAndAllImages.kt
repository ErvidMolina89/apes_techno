package com.example.apes_techno.DataAccess.DBLocal.ModelsDB

import androidx.room.Embedded
import androidx.room.Relation


class MovieAndAllImages {

    @Embedded
    var movie: Movie? = null

    @Relation(parentColumn = "Id",
        entityColumn = "MovieID")
    var images: Images = Images()
}