package com.sangeeta.yawa.ui.weather.today


import com.sangeeta.yawa.data.provider.UnitProvider
import com.sangeeta.yawa.data.repository.YawaRepository
import com.sangeeta.yawa.internal.lazyDeferred
import com.sangeeta.yawa.ui.base.WeatherViewModel

class TodaysWeatherViewModel(
    private val yawaRepository: YawaRepository,
    unitProvider: UnitProvider
) :WeatherViewModel(yawaRepository,unitProvider) {

    val weather  by lazyDeferred{
        yawaRepository.getTodaysWeather(unitSystem.name)
    }
}
