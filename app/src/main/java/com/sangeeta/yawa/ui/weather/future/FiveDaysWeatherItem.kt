package com.sangeeta.yawa.ui.weather.future

import com.bumptech.glide.Glide
import com.sangeeta.yawa.R
import com.sangeeta.yawa.data.network.response.nextfivedays.X
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_fivedays_weather.*

class FiveDaysWeatherItem (
    val weatherEntry: X
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_item_condition.text = weatherEntry.weather[0].description
            updateDate(weatherEntry.dtTxt)
            updateTemperature(weatherEntry.main.temp)
            updateConditionImage(weatherEntry.weather[0].icon)
        }
    }

    override fun getLayout() = R.layout.item_fivedays_weather

    private fun ViewHolder.updateDate(dtTxt: String) {
        textView_date.text = dtTxt



    }

    private fun ViewHolder.updateTemperature(temperature: Double) {

        textView__item_temperature.text = "$temperature"

    }



    private fun ViewHolder.updateConditionImage(icon: String) {
        Glide.with(this.containerView)
            .load("http://openweathermap.org/img/w/$icon.png")
            .into(imageView_weather_icon)

    }


}