package com.example.apes_techno.Base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("Registered")
class App: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var mContext: Context?= null
    }
}