package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Implementations

import android.content.Context
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieBL
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieListener
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMoviePresenter
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieView

class DetailsMovieFragmentPresenter (context: Context, view : IFragmentDetailsMovieView):
    IFragmentDetailsMoviePresenter{

    private val selectView : IFragmentDetailsMovieView
    private val context : Context
    private val selectItemBL : IFragmentDetailsMovieBL

    init {
        this.context = context
        this.selectView = view
        this.selectItemBL = DetailsMoviesFragmentBL(Listener(), context)
    }

    override fun callService(
        objectResponse: BaseModel,
        objectSend: IRetrofitParcelable,
        service: Services
    ) {
        selectItemBL.callService(objectResponse, objectSend, service)
    }



    private inner class Listener : IFragmentDetailsMovieListener {
        override fun responseDetailMovies(movies: MutableList<Movie>) {
            selectView.responseDetailMovies(movies)
        }

        override fun failureService(response: MessageResponse) {
            selectView.failureService(response)
        }
    }
}