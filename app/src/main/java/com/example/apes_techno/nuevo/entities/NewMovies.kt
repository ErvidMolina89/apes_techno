package com.example.apes_techno.nuevo.entities

import com.example.apes_techno.DataAccess.DBLocal.ModelsDB.Movie

class NewMovies(){
    var limit                   : Int? = null
    var number_of_page_results  : Int? = null
    var number_of_total_results : Int? = null
    var status_code             : Int? = null
    var results                 : MutableList<Movie>? = null
    var version                 : String? = null
}