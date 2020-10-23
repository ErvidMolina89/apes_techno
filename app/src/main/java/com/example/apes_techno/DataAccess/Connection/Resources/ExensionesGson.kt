package com.example.apes_techno.DataAccess.Connection.Resources

import com.example.apes_techno.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.google.gson.Gson

fun IRetrofitParcelable.ConvertirAObjeto(json: String): IRetrofitParcelable {
    val tmp = Gson().fromJson(json, this.javaClass)
    return tmp
}
