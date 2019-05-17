package com.sangeeta.yawa.data.network.response


import com.google.gson.annotations.SerializedName

data class Main(
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)