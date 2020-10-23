package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Implementations

import android.content.Context
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Item
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie
import com.example.apes_techno.DataAccess.Repositories.RepoService
import com.example.apes_techno.DataAccess.Repositories.IRepository
import com.example.apes_techno.DataAccess.Repositories.RepoSynchronization
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movies
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieBL
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieListener
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesBL
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesListener
import com.example.apes_techno.R
import com.example.apes_techno.Util.TypeService
import com.example.apes_techno.Util.isNetworkAvailable

class DetailsMoviesFragmentBL (listener : IFragmentDetailsMovieListener, context: Context):
    IFragmentDetailsMovieBL{

    private val synchronization = RepoSynchronization()
    private val context : Context
    private val listener : IFragmentDetailsMovieListener

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
                synchronization.getMovieAndImageItem(context).observeForever {
                    if (it == null) {
                        val msg = MessageResponse()
                        msg.Code = context.getString(R.string.Internet)
                        msg.Message =
                            context.resources.getString(R.string.detail_falla_Internet)
                        listener.failureService(msg)
                    } else {
                        var movie: Movie? = null
                        val movies = emptyList<Movie>().toMutableList()
                        val data = it
                        movie = Movie()
                        movie.id = data.movie?.id
                        movie.name = data.movie?.name
                        movie.runtime = data.movie?.runtime
                        movie.rating = data.movie?.rating
                        movie.release_date = data.movie?.release_date
                        movie.budget = data.movie?.budget
                        movie.image = data.images
                        movie.box_office_revenue = data.movie?.box_office_revenue
                        movie.description = data.movie?.description
                        movie.total_revenue = data.movie?.total_revenue
                        val producers = emptyList<Item>().toMutableList()
                        val studios = emptyList<Item>().toMutableList()
                        val writers = emptyList<Item>().toMutableList()
                        var count = 0

                        for (item in data.listItemMovie){
                            synchronization.getItemID(context, item.itemID!!).observeForever {
                                count += 1
                                when(it.type){
                                    TypeService.Producers.getType() -> {
                                        producers.add(it)
                                    }
                                    TypeService.Studios.getType() -> {
                                        studios.add(it)
                                    }
                                    TypeService.Writers.getType() -> {
                                        writers.add(it)
                                    }
                                }
                                if(count == data.listItemMovie.size){
                                    movie.producers = producers
                                    movie.studios = studios
                                    movie.writers = writers
                                    callDB(movies)
                                }
                            }

                        }
                        movies.add(movie)
                    }
                }
            }

        } catch (e: Exception) {

        }
    }

    private fun callDB(movies: MutableList<Movie>){
        listener.responseDetailMovies(movies)
    }

    private inner class ListenerRepositories : IRepository {

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.get_movie  -> {
                    if (objectResponse == null){return}
                    val response = objectResponse as Movies
                    listener.responseDetailMovies(response.results?.toMutableList()!!)
                }
                else -> {  }
            }
        }

        override fun onFailedResponse(response: MessageResponse, services: Services) {
            when(services){
                Services.get_movie   -> {
                    listener.failureService(response)
                }
                else -> { listener.failureService(response) }
            }
        }

    }

}