package com.example.apes_techno.Presentations.Fragments.FragmentHomeMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.apes_techno.R

class FragmentHomeMovies : Fragment() {

    var textView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_movies, container, false)
        textView = view.findViewById(R.id.text_home)
        textView?.text = "Peliculas Populares"
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView?.setOnClickListener {
            findNavController().navigate(R.id.action_detail_movie)
        }
    }
}