package com.example.apes_techno.nuevo.di.module

import com.example.apes_techno.nuevo.di.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule (val baseApplication: BaseApplication) {
//    @ApplicationScope
    @Provides
    fun provideApplication() = baseApplication

    @Provides
    fun provideApplicationContext() = baseApplication.applicationContext
}