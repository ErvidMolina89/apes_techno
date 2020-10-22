package com.example.apes_techno.Presentations.Fragments.FragmentDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apes_techno.R

class DetailsMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_movie, container, false)
        val textView: TextView = view.findViewById(R.id.text_dashboard)
        textView.text = "Detalle de Pelicula"
        return view
    }
}