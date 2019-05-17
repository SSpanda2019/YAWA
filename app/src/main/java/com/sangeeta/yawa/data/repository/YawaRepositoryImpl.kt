package com.sangeeta.yawa.data.repository

import androidx.lifecycle.LiveData
import com.sangeeta.yawa.data.network.WeatherNetworkDataSource
import com.sangeeta.yawa.data.network.response.nextfivedays.FiveDaysWeatherResponse
import com.sangeeta.yawa.data.network.response.TodaysWeatherResponse
import com.sangeeta.yawa.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class YawaRepositoryImpl(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : YawaRepository {
    override suspend fun getTodaysWeather(unitVal: String): LiveData<TodaysWeatherResponse> {
        fetchTodaysWeather(unitVal)
        return withContext(Dispatchers.IO) {
            return@withContext weatherNetworkDataSource.downloadTodaysWeather
        }
    }

    private suspend fun fetchTodaysWeather(unitVal: String) {
        weatherNetworkDataSource.fetchTodaysWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language,
            unitVal)
    }
    override suspend fun getFivedaysWeather(unitVal: String): LiveData<FiveDaysWeatherResponse> {
        fetchFiveDaysWeather(unitVal)
        return withContext(Dispatchers.IO){
            return@withContext weatherNetworkDataSource.downloadedFivedaysWeather
        }
    }
    private suspend fun fetchFiveDaysWeather(unitVal: String) {
        weatherNetworkDataSource.fetchFiveDaysWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language,
            unitVal)
    }
}