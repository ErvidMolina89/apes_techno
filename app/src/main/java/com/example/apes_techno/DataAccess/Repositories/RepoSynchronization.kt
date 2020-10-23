package com.example.apes_techno.DataAccess.Repositories

import android.content.Context
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.*
import com.example.apes_techno.DataAccess.DBLocal.DBApes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepoSynchronization {

    fun onInsertMovie(context: Context, movies: MutableList<Movie>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            DBApes.getInstance(context).movieDao().insertList(movies)
        }
    }

    fun onInsertItems(context: Context, items: Item, onSuccessInsert: (Long) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBApes.getInstance(context).itemDao().insert(items))
        }
    }

    fun onInsertItemMovie(context: Context, items: ItemMovie, onSuccessInsert: (Long) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBApes.getInstance(context).itemMovieDao().insert(items))
        }
    }

    fun onInsertImage(context: Context, image: Images){
        GlobalScope.launch {
            DBApes.getInstance(context).imagesDao().insert(image)
        }
    }

    fun deleteAllMovie(context: Context) =
        GlobalScope.launch {
        DBApes.getInstance(context).movieDao().nukeMovie()
        }

    fun deleteAllItems(context: Context) =
        GlobalScope.launch {
            DBApes.getInstance(context).itemDao().nukeItem()
        }

    fun deleteAllImage(context: Context) =
        GlobalScope.launch {
            DBApes.getInstance(context).imagesDao().nukeImages()
        }

    fun getAllItem(context: Context)
            = DBApes.getInstance(context).itemDao().getItems()

    fun getItemID(context: Context, id: Int)
            = DBApes.getInstance(context).itemDao().getItemID(id)

    fun getAllItemMovie(context: Context)
            = DBApes.getInstance(context).itemMovieDao().getItemMovies()

    fun getAllMovie(context: Context)
            = DBApes.getInstance(context).movieDao().getAllMovie()

    fun getMovies(context: Context)
            = DBApes.getInstance(context).movieDao().getMovie()

    fun getMovieAndImageItem(context: Context)
            = DBApes.getInstance(context).movieDao().getMovieAndImageItem()

    fun getAllImages(context: Context)
            = DBApes.getInstance(context).imagesDao().getImages()

    }