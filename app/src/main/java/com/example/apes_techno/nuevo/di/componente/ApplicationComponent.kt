package com.example.apes_techno.nuevo.di.componente

import com.example.apes_techno.nuevo.di.module.ApplicationContextModule
import dagger.Component

@Component(modules = [
    ApplicationContextModule::class
])
interface ApplicationComponent {
}