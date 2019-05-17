package com.sangeeta.yawa.data.network.response

data class TodaysWeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val dt: Int,
    val sys: Sys,
    val id: Int,
    val name: String

)