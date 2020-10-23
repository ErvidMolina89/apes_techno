package com.example.apes_techno.DataAccess.DBLocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.*
import com.example.quenta.DataAccess.DBLocal.Daos.*

@Database(entities = arrayOf(
    Movie::class,
    Images::class,
    Item::class,
    ItemMovie::class
), version = 1)
abstract class DBApes : RoomDatabase() {

    abstract fun imagesDao(): ImagesDao
    abstract fun itemDao(): ItemDao
    abstract fun movieDao(): MovieDao
    abstract fun itemMovieDao(): ItemMovieDao

    companion object {
        private const val nameDB = "ApesDBExample"
        @Volatile
        private var INSTANCE: DBApes? = null

        fun getInstance(context: Context): DBApes =
                INSTANCE ?: synchronized(this) {
                    buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room
                    .databaseBuilder(context.applicationContext, DBApes::class.java, nameDB)
                    .build()
    }

}