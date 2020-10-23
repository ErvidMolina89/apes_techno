package com.example.apes_techno.Presentations.Fragments.FragmentDetails.Implementations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apes_techno.Base.App
import com.example.apes_techno.Base.BaseFragment
import com.example.apes_techno.Connection.Resources.Services
import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.example.apes_techno.Models.BaseModel
import com.example.apes_techno.Models.MessageResponse
import com.example.apes_techno.Models.Movie
import com.example.apes_techno.Models.Movies
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMoviePresenter
import com.example.apes_techno.Presentations.Fragments.FragmentDetails.Interfaces.IFragmentDetailsMovieView
import com.example.apes_techno.R
import com.example.apes_techno.Util.DialogueGenerico
import com.example.apes_techno.Util.hiddenProgress
import com.example.apes_techno.Util.showImage
import com.example.apes_techno.Util.showProgress
import kotlinx.android.synthetic.main.fragment_detail_movie.*

class DetailsMovieFragment : BaseFragment() {

    private var presenter: IFragmentDetailsMoviePresenter? = null
    private var actionPresenter = actionViewPresenter()
    var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_movie, container, false)
        presenter = DetailsMovieFragmentPresenter(App.mContext!!, actionPresenter)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val args = DetailsMovieFragmentArgs.fromBundle(requireArguments())
            id = args.idMovie
        }
    }

    override fun onResume() {
        super.onResume()
        callService(
            Movies(), object :
            IRetrofitParcelable {},
            Services.get_movie.getUrlWithComplement(id.toString()+'&'))
        context?.showProgress()
    }

    fun callService(objectResponse: BaseModel,
                    objectSend: IRetrofitParcelable,
                    service: Services
    ){
        presenter?.callService(objectResponse, objectSend, service)
    }

    inner class actionViewPresenter: IFragmentDetailsMovieView {
        override fun failureService(response: MessageResponse) {
            context?.hiddenProgress()
            dialogueFragment(response.Code.toString(), response.Message!!, DialogueGenerico.TypeDialogue.ERROR)
        }

        override fun responseDetailMovies(movies: MutableList<Movie>) {
            context?.hiddenProgress()
            visualizarMovie(movies[0])
        }
    }

    private fun visualizarMovie(item: Movie){
        iv_detail_movie.showImage(item.image?.original_url)
        tv_name_detail.text         = item.name
        val date = item.release_date?.split(" ")
        tv_date_detail.text         = date!![0]
        tv_rating_detail.text       = item.rating
        tv_runtime_detail.text      = item.runtime
        tv_budget_detail.text       = item.budget
        if(item.description != null){
            val description1 = item.description?.split("<p>")
            val description = description1!![1].split("</p>")
            tv_description_detail.text  = description[0]
        }else tv_description_detail.text  = ""
        tv_boxOR_detail.text        = item.box_office_revenue
        tv_TotalGR_detail.text      = item.total_revenue

        var producers_t = ""
        var producers = ""
        if (item.producers != null) {
            for (producer in item.producers!!) {
                producers_t += producer.name + ", "
            }
            producers = producers_t.subSequence(0, (producers_t.length - 2)) as String
        }
        tv_producers_detail.text    = producers

        var writer_t = ""
        var writer = ""
        if (item.writers != null) {
            for (producer in item.writers!!) {
                writer_t += producer.name + ", "
            }
            writer = writer_t.subSequence(0, (writer_t.length - 2)) as String
        }
        tv_writer_detail.text    = writer

        var studio_t = ""
        var studio = ""
        if (item.studios != null) {
            for (producer in item.studios!!) {
                studio_t += producer.name + ", "
            }
            studio = studio_t.subSequence(0, (studio_t.length - 2)) as String
        }
        tv_studio_detail.text    = studio
    }
}