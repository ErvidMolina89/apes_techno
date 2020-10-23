package com.example.apes_techno.Util

enum class TypeService {
    Producers,
    Studios,
    Writers

    ;

    fun getType() : String{
        return when(this){
            Producers -> {"1"}
            Studios -> {"2"}
            Writers -> {"3"}
        }
    }
}