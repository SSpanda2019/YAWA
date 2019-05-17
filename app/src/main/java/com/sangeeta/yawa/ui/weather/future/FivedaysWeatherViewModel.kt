package com.sangeeta.yawa.ui.weather.future

import androidx.lifecycle.ViewModel;
import com.sangeeta.yawa.data.provider.UnitProvider
import com.sangeeta.yawa.data.repository.YawaRepository
import com.sangeeta.yawa.internal.lazyDeferred
import com.sangeeta.yawa.ui.base.WeatherViewModel

class FivedaysWeatherViewModel (
    private val yawaRepository: YawaRepository,
    private val unitProvider: UnitProvider

) : WeatherViewModel(yawaRepository, unitProvider) {

    val weather  by lazyDeferred{
        yawaRepository.getFivedaysWeather(unitSystem.name)
    }
}
