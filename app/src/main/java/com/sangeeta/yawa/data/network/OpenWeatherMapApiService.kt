package com.sangeeta.yawa.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sangeeta.yawa.data.network.response.nextfivedays.FiveDaysWeatherResponse
import com.sangeeta.yawa.data.network.response.TodaysWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6f8fa5bfd8aa802df69da9b594406dad"

interface OpenWeatherMapApiService {

    //http://api.openweathermap.org/data/2.5/weather?q=Auckland&APPID=6f8fa5bfd8aa802df69da9b594406dad&units=mertic

    @GET("weather")
    fun getTodaysweather(
        @Query("q") location: String,
        @Query("lang") lanuageCode: String="en",
        @Query("units") unitM: String="mertic"
    ): Deferred<TodaysWeatherResponse>

    //   http://api.openweathermap.org/data/2.5/forecast?q=Auckland&APPID=6f8fa5bfd8aa802df69da9b594406dad&units=metric
    @GET("forecast")
    fun getFivedaysWeather(
        @Query("q") location: String,
        @Query("lang") lanuageCode: String="en",
        @Query("units") unitM: String="mertic"
    ):Deferred<FiveDaysWeatherResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ):OpenWeatherMapApiService{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("APPID", API_KEY)
                    .build()
                val request  = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherMapApiService::class.java)
        }
    }
}