@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.apes_techno.Util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.apes_techno.Base.App
import com.example.apes_techno.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

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

fun ImageView.showImage(urlFoto : String? = null ) {
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

@SuppressLint("SimpleDateFormat")
fun Date.convertFormato(formato : FormateDates) : String {
    val parseador = SimpleDateFormat(formato.getFormato())
    return parseador.format(this)
}

@SuppressLint("SimpleDateFormat")
fun String.convertADate(formato :FormateDates = FormateDates.ISO_8601) : Date{
    val parseador = SimpleDateFormat(formato.getFormato())
    return parseador.parse(this)
}

fun Context.showProgress(){
    if(this !is AppCompatActivity){
        return
    }else{
        runOnUiThread {
            ProgressBarPersonalized
                .getInstancia()
                .show(supportFragmentManager,"progressbar")
        }

        GlobalScope.launch {
            delay(30_000)
            hiddenProgress()
        }
    }
}

fun Context.hiddenProgress(){
    if(this !is AppCompatActivity){
        return
    }else{
        runOnUiThread {
            ProgressBarPersonalized
                .getInstancia()
                .dismiss()

        }
    }
}

fun String.changeFormateDate(formatoEntrada : FormateDates, formatoSalida: FormateDates) : String{
    return this.convertADate(formatoEntrada).convertFormato(formatoSalida)
}

fun String.showToast() : String{
    Toast.makeText(App.mContext!!.applicationContext,this, Toast.LENGTH_LONG).show()
    return this
}