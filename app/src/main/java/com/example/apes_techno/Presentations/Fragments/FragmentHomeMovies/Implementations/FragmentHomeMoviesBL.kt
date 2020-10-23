package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Implementations

import android.content.Context
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Images
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Item
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.ItemMovie
import com.example.apes_techno.DataAccess.Repositories.RepoService
import com.example.apes_techno.DataAccess.Repositories.IRepository
import com.example.apes_techno.DataAccess.Repositories.RepoSynchronization
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movies
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesBL
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesListener
import com.example.apes_techno.R
import com.example.apes_techno.Util.TypeService
import com.example.apes_techno.Util.isNetworkAvailable

class FragmentHomeMoviesBL (listener : IFragmentHomeMoviesListener, context: Context):
    IFragmentHomeMoviesBL {

    private val synchronization = RepoSynchronization()
    private val context : Context
    private val listener : IFragmentHomeMoviesListener

    init {
        this.context = context
        this.listener = listener
    }
    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        try {
            if (isNetworkAvailable(context)) {
                RepoService(context).callService(
                    objectResponse,
                    objectSend,
                    service,
                    ListenerRepositories())
            } else {
                synchronization.getMovies(context).observeForever {
                    if (it?.size == 0) {
                        val msg = MessageResponse()
                        msg.Code = context.getString(R.string.Internet)
                        msg.Message =
                            context.resources.getString(R.string.detail_falla_Internet)
                        listener.failureService(msg)
                    } else {
                        var movie: Movie? = null
                        val movies = emptyList<Movie>().toMutableList()
                        for (data in it){
                            movie = Movie()
                            movie.id = data.movie?.id
                            movie.name = data.movie?.name
                            movie.runtime = data.movie?.runtime
                            movie.release_date = data.movie?.release_date
                            movie.image = data.images
                            movies.add(movie)
                        }
                        listener.responseHomeMovies(movies)
                    }
                }
            }

        } catch (e: Exception) {

        }
    }

    private inner class ListenerRepositories : IRepository {

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.get_list_movies  -> {
                    if (objectResponse == null){return}
                    val response = objectResponse as Movies
                    listener.responseHomeMovies(response.results?.toMutableList()!!)
                    synchronization.getAllMovie(context).observeForever {
                        if (it.size == 0){
                            synchronizationBD(response)
                        }
                    }
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.get_list_movies   -> {
                    listener.failureService(response)
                }
                else -> { listener.failureService(response) }
            }
        }
    }

    private fun synchronizationBD(request: Movies){
        synchronization.onInsertMovie(context, request.results!!)
        for (movie in request.results!!){
            if (movie.image != null) {
                synchronizationImageBD(movie.image!!, movie.id!!)
            }
            if (movie.producers != null){
                for (producer in movie.producers!!){
                    synchronizationItemProducersBD(producer, movie.id!!)
                }
            }
            if (movie.studios != null) {
                for (studio in movie.studios!!) {
                    synchronizationItemStudiosBD(studio, movie.id!!)
                }
            }
            if (movie.writers != null) {
                for (writer in movie.writers!!) {
                    synchronizationItemWritersBD(writer, movie.id!!)
                }
            }
        }
    }

    private fun synchronizationImageBD(image: Images, id: Int){
        image.movieID = id
        synchronization.onInsertImage(context, image)
    }

    private fun synchronizationItemProducersBD(item: Item, id: Int){
        item.type = TypeService.Producers.getType()
        synchronization.onInsertItems(context, item, ::onSuccessInsertProducer)
        val itemMovie = ItemMovie()
        itemMovie.itemID = item.id
        itemMovie.movieID = id
        synchronization.onInsertItemMovie(context, itemMovie)
    }

    private fun synchronizationItemStudiosBD(item: Item, id: Int){
        item.type = TypeService.Studios.getType()
        synchronization.onInsertItems(context, item)
        val itemMovie = ItemMovie()
        itemMovie.itemID = item.id
        itemMovie.movieID = id
        synchronization.onInsertItemMovie(context, itemMovie)
    }

    private fun synchronizationItemWritersBD(item: Item, id: Int){
        item.type = TypeService.Writers.getType()
        synchronization.onInsertItems(context, item)
        val itemMovie = ItemMovie()
        itemMovie.itemID = item.id
        itemMovie.movieID = id
        synchronization.onInsertItemMovie(context, itemMovie)
    }

    private fun onSuccessInsertProducer(id: Long){
        val result = id
    }

}