package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Implementations

import android.content.Context
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Interfaces.*

class FragmentHomeMoviesPresenter (context: Context, view : IFragmentHomeMoviesView):
    IFragmentHomeMoviesPresenter{

    private val selectView : IFragmentHomeMoviesView
    private val context : Context
    private val selectItemBL : IFragmentHomeMoviesBL

    init {
        this.context = context
        this.selectView = view
        this.selectItemBL = FragmentHomeMoviesBL(Listener(), context)
    }

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        selectItemBL.callService(objectResponse, objectSend, service)
    }



    private inner class Listener : IFragmentHomeMoviesListener {
        override fun responseHomeMovies(movies: MutableList<Movie>) {
            selectView.responseHomeMovies(movies)
        }

        override fun failureService(response: MessageResponse) {
            selectView.failureService(response)
        }
    }
}