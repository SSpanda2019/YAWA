package com.sangeeta.yawa.ui.weather.future

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sangeeta.yawa.data.provider.UnitProvider
import com.sangeeta.yawa.data.repository.YawaRepository


class FivedaysWeatherViewModelFactory (
    private  val yawaRepository: YawaRepository,
    private val unitProvider: UnitProvider
):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FivedaysWeatherViewModel(yawaRepository,unitProvider) as T
    }
}