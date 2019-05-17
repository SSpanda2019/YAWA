package com.sangeeta.yawa.data.repository

import androidx.lifecycle.LiveData
import com.sangeeta.yawa.data.network.response.TodaysWeatherResponse
import com.sangeeta.yawa.data.network.response.nextfivedays.FiveDaysWeatherResponse

interface YawaRepository {
    suspend fun getTodaysWeather(unitVal: String): LiveData<TodaysWeatherResponse>
    suspend fun getFivedaysWeather(metric: String): LiveData<FiveDaysWeatherResponse>
}