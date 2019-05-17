package com.sangeeta.yawa.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sangeeta.yawa.data.network.response.TodaysWeatherResponse
import com.sangeeta.yawa.data.network.response.nextfivedays.FiveDaysWeatherResponse
import com.sangeeta.yawa.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val openWeatherMapApiService: OpenWeatherMapApiService
) : WeatherNetworkDataSource {

    private val _downloadTodaysWeather = MutableLiveData<TodaysWeatherResponse>()
    override val downloadTodaysWeather: LiveData<TodaysWeatherResponse>
        get() = _downloadTodaysWeather
    private val _downloadedFivedaysWeather = MutableLiveData<FiveDaysWeatherResponse>();
    override val downloadedFivedaysWeather: LiveData<FiveDaysWeatherResponse>
        get() = _downloadedFivedaysWeather

    override suspend fun fetchTodaysWeather(location: String, languageCode: String, unitM: String) {
        try{
            val fetchedTodaysWeather = openWeatherMapApiService
                .getTodaysweather(location, languageCode,unitM)
                .await()
            _downloadTodaysWeather.postValue(fetchedTodaysWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchFiveDaysWeather(location: String, languageCode: String, unitM: String) {
        try{
            val fetchedFutureWeather = openWeatherMapApiService
                .getFivedaysWeather(location, languageCode,unitM)
                .await()
            _downloadedFivedaysWeather.postValue(fetchedFutureWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}