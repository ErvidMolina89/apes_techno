package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Complements

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apes_techno.Base.App
import com.example.apes_techno.Models.Movie
import com.example.apes_techno.R

class ViewListMovies @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_list_rv_movies,this,true)
        searchViewItems()
        paintMovieItemList()
    }

    private var rv_list_general_gallery : RecyclerView? = null

    private fun searchViewItems(){
        rv_list_general_gallery = findViewById(R.id.rv_list_movies_home)
    }

    private var adapterMoviesItemList  : AdapterMoviesItemList? = null
    private fun paintMovieItemList(){
        this.adapterMoviesItemList = AdapterMoviesItemList()
            .conContext(App.mContext!!)
            .conRecyclerView(rv_list_general_gallery!!)
            .conListenerMoviesItemList {
                listenerListMovies?.invoke(it)
            }
    }

    private var listenerListMovies: ((Movie)-> Unit)? = null
    fun conListenerMovieList(listenerListGallery: (Movie)-> Unit): ViewListMovies {
        this.listenerListMovies = listenerListGallery
        return this
    }

    fun conVisualizeListMovies(list: MutableList<Movie>): ViewListMovies {
        post {
            adapterMoviesItemList
                ?.conListMovies(list)
                ?.cargarRecycler()
        }
        return this
    }

    fun showView(){
        post {
            visibility = View.VISIBLE
            paintMovieItemList()
        }
    }

    fun hideView(){
        post {
            visibility = View.GONE
        }
    }
}