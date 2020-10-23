package com.example.apes_techno.Util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.apes_techno.Base.App
import com.example.apes_techno.R
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Exception

/**
 * Created by exsis on 5/01/18.
 */

fun Context.showDialogueGenerico(){
    if(this !is AppCompatActivity){ return }
    DialogueGenerico.getInstance().showDialogueGenerico(supportFragmentManager,"DialogoGenerico")
}

fun CircleImageView.showImage(urlFoto : String? = null ) {
    if (urlFoto.isNullOrEmpty()) { return }
    Glide
        .with(context)
        .load(urlFoto)
        .dontAnimate()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.drawable.playa_la_roca)
        .skipMemoryCache(true)
        .centerCrop()
        .into(this)
}

fun isNetworkAvailable(context: Context): Boolean {
    try {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    } catch (e: Exception) {
        return false
    }
}

fun String.mostrarEnToast() : String{
    Toast.makeText(App.mContext!!.applicationContext,this, Toast.LENGTH_LONG).show()
    return this
}