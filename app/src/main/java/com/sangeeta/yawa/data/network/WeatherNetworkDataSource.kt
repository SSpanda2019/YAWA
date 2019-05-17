package com.sangeeta.yawa.data.network

import androidx.lifecycle.LiveData
import com.sangeeta.yawa.data.network.response.TodaysWeatherResponse
import com.sangeeta.yawa.data.network.response.nextfivedays.FiveDaysWeatherResponse

interface WeatherNetworkDataSource {
    val downloadTodaysWeather : LiveData<TodaysWeatherResponse>
    val downloadedFivedaysWeather : LiveData<FiveDaysWeatherResponse>
    suspend  fun  fetchTodaysWeather(
        location: String,
        languageCode: String,
        unitM :String
    )
    suspend  fun  fetchFiveDaysWeather(
        location: String,
        languageCode: String,
        unitM :String
    )
}