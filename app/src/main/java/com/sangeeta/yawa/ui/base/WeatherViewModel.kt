package com.sangeeta.yawa.ui.base

import androidx.lifecycle.ViewModel
import com.sangeeta.yawa.data.provider.UnitProvider
import com.sangeeta.yawa.data.repository.YawaRepository
import com.sangeeta.yawa.internal.UnitSystem

abstract class WeatherViewModel(
    private val yawaRepository: YawaRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC


}