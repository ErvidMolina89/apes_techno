package com.example.apes_techno.nuevo.di.componente

import com.example.apes_techno.nuevo.di.module.ApplicationContextModule
import com.example.apes_techno.nuevo.di.module.MoviesModule
import com.example.apes_techno.nuevo.ui.activities.Main2Activity
import dagger.Component

@Component(modules = [
    ApplicationContextModule::class,
    MoviesModule::class
])
interface MoviesComponent {

    fun inject(main2Activity: Main2Activity)
}