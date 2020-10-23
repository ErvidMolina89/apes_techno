package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Implementations

import android.content.Context
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.DataAccess.Repositories.RepoService
import com.example.apes_techno.DataAccess.Repositories.IRepository
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movies
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesBL
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesListener

class FragmentHomeMoviesBL (listener : IFragmentHomeMoviesListener, context: Context):
    IFragmentHomeMoviesBL {

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
        RepoService(context).callService(objectResponse, objectSend, service, ListenerRepositories())
    }

    private inner class ListenerRepositories : IRepository {

        override fun onSuccessResponse(objectResponse: Any?, services: Services) {
            when(services){
                Services.get_list_movies  -> {
                    if (objectResponse == null){return}
                    val response = objectResponse as Movies
                    listener.responseHomeMovies(response.results?.toMutableList()!!)
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

}