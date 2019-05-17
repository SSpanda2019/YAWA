package com.sangeeta.yawa.data.network.response.nextfivedays


data class FiveDaysWeatherResponse(
    val message: Double,
    val cnt: Int,
    val list: List<X>,
    val city: City
)