package com.example.apes_techno.nuevo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apes_techno.R
import com.example.apes_techno.databinding.ActivityMain2Binding
import com.example.apes_techno.nuevo.di.BaseApplication
import com.example.apes_techno.nuevo.entities.NewMovie
import com.example.apes_techno.nuevo.entities.NewMovies
import com.example.apes_techno.nuevo.presenter.movies.ViewModelMovie
import javax.inject.Inject

class Main2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    @Inject
    lateinit var viewModelMovies: ViewModelMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityPrincipal = NewMovies().apply{
            version = "YesidVersion"
        }
        setUpDagger()
    }

    fun setUpDagger(){
        (this.applicationContext as BaseApplication).getMoviesComponent().inject(this)
        Log.e("", "")
    }

}