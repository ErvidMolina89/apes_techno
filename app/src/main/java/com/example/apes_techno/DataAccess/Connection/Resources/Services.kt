package com.example.apes_techno.Connection.Resources

import com.example.apes_techno.DataAccess.Connection.Handler.IServiceParameters

enum class Services (url : String,
                     method : IServiceParameters.Methods)
    : IServiceParameters {

    get_list_movies("movies/",IServiceParameters.Methods.GET),
    get_movie("movies/?filter=",IServiceParameters.Methods.GET)
    ;

    private val url : String
    private val method : IServiceParameters.Methods
    private var complement: String = ""

    init {
        this.url = url
        this.method = method
    }

    override fun getURL(): String {
        return url + complement
    }

    override fun getMethods(): IServiceParameters.Methods {
        return method
    }

    override fun getUrlWithComplement(complement: String): Services {
        this.complement = complement
        return this
    }

}