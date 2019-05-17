package com.sangeeta.yawa.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


const val ENTER_LOCATION = "ENTER_LOCATION"
class LocationProviderImpl(
    context: Context
) : LocationProvider {

    private val appContext = context.applicationContext
    private  val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
    override suspend fun getPreferredLocationString(): String {
        return preferences.getString(ENTER_LOCATION, "Auckland")
    }
}