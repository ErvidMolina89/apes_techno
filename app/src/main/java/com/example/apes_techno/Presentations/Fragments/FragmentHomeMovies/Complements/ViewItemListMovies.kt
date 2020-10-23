package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies.Complements

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.apes_techno.Models.Movie
import com.example.apes_techno.R
import com.example.apes_techno.Util.showImage
import de.hdodenhof.circleimageview.CircleImageView

class ViewItemListMovies @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)  {

    init {
        LayoutInflater.from(context).inflate(R.layout.fragment_movies,this,true)
        searchViewItems()
        addViewListeners()
    }

    private var cardView        : CardView ?= null
    private var image           : CircleImageView?= null
    private var nameMovie       : TextView ?= null
    private var dateMovie       : TextView ?= null
    private var runtimeMovie    : TextView ?= null

    private fun searchViewItems(){
        cardView        = findViewById(R.id.card_container_item)
        image           = findViewById(R.id.imageview_avatar)
        nameMovie       = findViewById(R.id.textview_titulo_name)
        dateMovie       = findViewById(R.id.textview_date)
        runtimeMovie    = findViewById(R.id.textview_runtime)
    }

    private fun addViewListeners(){
        cardView?.setOnClickListener {
            listenerItem?.invoke(item!!)
        }
    }

    private var item : Movie ?= null
    fun conItem(item : Movie): ViewItemListMovies {
        this.item = item
        return this
    }

    fun updateView() : ViewItemListMovies {
        if(item == null){
            return this
        }

        post {
            updateImage()
            updateLabels()
        }
        return this
    }

    private fun updateImage(){
        if(item?.image == null ){ return }
        image?.showImage(item!!.image?.small_url)
    }

    @SuppressLint("SetTextI18n")
    private fun updateLabels() {
        try {
            val date = item!!.release_date?.split(" ")
            nameMovie?.setText(item!!.name)
            dateMovie?.setText(date!![0])
            runtimeMovie?.setText(item!!.runtime)
        }catch (e: Exception){

        }
    }

    private var listenerItem: ((Movie)->Unit)? = null
    fun conListenerItem(listenerItem: ((Movie)->Unit)): ViewItemListMovies {
        this.listenerItem = listenerItem
        return this
    }

}