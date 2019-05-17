package com.sangeeta.yawa.ui.weather.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.sangeeta.yawa.R

import com.sangeeta.yawa.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.todays_weather_fragment.*

import kotlinx.coroutines.launch

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TodaysWeatherFragment : ScopedFragment(),KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: TodaysWeatherViewModelFactory by instance()


    private lateinit var viewModel: TodaysWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todays_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TodaysWeatherViewModel::class.java)
        bindUI()

    }

    private fun bindUI() = launch {
        this@TodaysWeatherFragment
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@TodaysWeatherFragment, Observer {

            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            group_loading.visibility = View.GONE
            updateDateToToday()
            updateTemperatures(it.main.temp, it.main.tempMin, it.main.tempMax)
            updateCondition(it.weather[0].main)
            updateWeatherCondition(it.weather[0].description)
            updateWind(it.wind.deg, it.wind.speed)
            updateLocation(it.name)

        Glide.with(this@TodaysWeatherFragment)
            .load("http://openweathermap.org/img/w/${it.weather[0].icon}.png")
            .into(imageView_condition_icon)


        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetricUnit) metric else imperial
    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, tempMin: Double, tempMax: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_min_max_temperature.text = "$tempMin$unitAbbreviation" + "/" + "$tempMax$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updateWeatherCondition(weatherDescription: String) {
        textView_weather_description.text = "Description: $weatherDescription "
    }

    private fun updateWind(windDegree: Double, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind Speed: $windSpeed$unitAbbreviation, $windDegree°"
    }

}