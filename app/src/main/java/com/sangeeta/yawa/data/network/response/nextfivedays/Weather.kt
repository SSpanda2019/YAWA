package com.sangeeta.yawa.data.network.response.nextfivedays


data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)