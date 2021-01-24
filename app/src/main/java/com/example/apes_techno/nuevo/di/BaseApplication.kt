package com.example.apes_techno.nuevo.di

import android.app.Application
import com.example.apes_techno.nuevo.di.componente.ApplicationComponent
import com.example.apes_techno.nuevo.di.componente.DaggerApplicationComponent
import com.example.apes_techno.nuevo.di.componente.DaggerMoviesComponent
import com.example.apes_techno.nuevo.di.componente.MoviesComponent
import com.example.apes_techno.nuevo.di.module.ApplicationContextModule
import com.example.apes_techno.nuevo.di.module.MoviesModule

class BaseApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var moviesComponent: MoviesComponent

    override fun onCreate() {
        super.onCreate()
        setupApplicationComponent()
        setupMoviesComponent()
    }

    private fun setupApplicationComponent() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }

    private fun setupMoviesComponent() {
        moviesComponent = DaggerMoviesComponent
            .builder()
            .moviesModule(MoviesModule())
            .build()
    }

    fun getApplicationComponent() = applicationComponent
    fun getMoviesComponent() = moviesComponent
}