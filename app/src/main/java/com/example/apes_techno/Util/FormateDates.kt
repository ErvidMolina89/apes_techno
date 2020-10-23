package com.example.apes_techno.Util

enum class FormateDates {
    ISO_8601,
    ISO_8601_CON_MILISEGUNDOS,
    HORA,
    HORA_SEGUNDOS,
    FECHA,
    diaSemana_mesdia_anio,
    diasemana_dia_mes_anio,

    ;

    fun getFormato() : String{
        return when(this){
            ISO_8601 -> { "yyyy-MM-dd'T'HH:mm:ss" }
            ISO_8601_CON_MILISEGUNDOS -> {"yyyy-MM-dd'T00:00:00.000Z'"}
            HORA -> { "HH:mm" }
            HORA_SEGUNDOS -> { "HH:mm:ss" }
            FECHA -> { "yyyy-MM-dd" }
            diaSemana_mesdia_anio -> { "EEE, MMM dd, yyyy" }
            diasemana_dia_mes_anio -> { " EEE dd MMM yyyy " }
        }
    }
}