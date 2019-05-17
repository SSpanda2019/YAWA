package com.sangeeta.yawa.data.network.response.nextfivedays


import com.google.gson.annotations.SerializedName

data class X(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val rain: Rain
)