package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Complements

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apes_techno.Models.Movie
import com.example.apes_techno.R


class AdapterMoviesItemList : RecyclerView.Adapter<AdapterMoviesItemList.itemListGallery>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemListGallery {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_lista_items_movie, null, false)
        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 400)
        view.layoutParams = params
        return itemListGallery(view)

    }

    override fun getItemCount(): Int {
        Log.e("Tamaño lista", listaMovies.size.toString())

        return listaMovies.size
    }

    override fun onBindViewHolder(holder: itemListGallery, position: Int) {
        val viewHolder = holder.view.findViewById<ViewItemListMovies>(R.id.recycler_item_list_movie)
        viewHolder
            .conItem(listaMovies[position])
            .conListenerItem {
                listenerItemMovie?.invoke(it)
            }
            .updateView()

    }

    inner class itemListGallery(val view: View): RecyclerView.ViewHolder(view)

    private var context: Context? = null
    fun conContext(context: Context): AdapterMoviesItemList {
        this.context = context
        return this
    }

    private var recyclerView: RecyclerView? = null
    fun conRecyclerView(recyclerView: RecyclerView): AdapterMoviesItemList {
        this.recyclerView = recyclerView
        return this
    }

    private var listaMovies = emptyList<Movie>().toMutableList()
    fun conListMovies (list: MutableList<Movie>): AdapterMoviesItemList {
        this.listaMovies = list
        return this
    }

    private var listenerItemMovie: ((Movie)-> Unit)? = null
    fun conListenerMoviesItemList(escuchador: (Movie)-> Unit): AdapterMoviesItemList {
        this.listenerItemMovie = escuchador
        return this
    }

    fun cargarRecycler(){

        recyclerView?.post {
            recyclerView?.adapter = null
            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = this
        }

    }
}