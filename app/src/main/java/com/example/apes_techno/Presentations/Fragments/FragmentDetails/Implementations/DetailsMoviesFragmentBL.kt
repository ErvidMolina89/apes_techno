package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Implementations

import android.content.Context
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.DataAccess.Repositories.RepoService
import com.example.apes_techno.DataAccess.Repositories.IRepository
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movies
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieBL
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieListener
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesBL
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.IFragmentHomeMoviesListener

class DetailsMoviesFragmentBL (listener : IFragmentDetailsMovieListener, context: Context):
    IFragmentDetailsMovieBL{

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
        RepoService(context).callService(objectResponse, objectSend, service, ListenerRepositories())
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