package com.sangeeta.yawa.data.network.response.nextfivedays


data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int
)