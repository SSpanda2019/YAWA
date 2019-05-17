package com.sangeeta.yawa

import android.app.Application
import androidx.preference.PreferenceManager
import com.sangeeta.yawa.data.network.*
import com.sangeeta.yawa.data.provider.LocationProvider
import com.sangeeta.yawa.data.provider.LocationProviderImpl
import com.sangeeta.yawa.data.provider.UnitProvider
import com.sangeeta.yawa.data.provider.UnitProviderImpl
import com.sangeeta.yawa.data.repository.YawaRepository
import com.sangeeta.yawa.data.repository.YawaRepositoryImpl
import com.sangeeta.yawa.ui.weather.future.FivedaysWeatherViewModelFactory
import com.sangeeta.yawa.ui.weather.today.TodaysWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class YawaApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@YawaApplication))
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { OpenWeatherMapApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>()with singleton { LocationProviderImpl(instance()) }
        bind<YawaRepository>() with singleton { YawaRepositoryImpl(instance(),instance()) }
        bind<UnitProvider>()with singleton { UnitProviderImpl(instance()) }
        bind() from provider { TodaysWeatherViewModelFactory(instance(),instance()) }
        bind() from provider { FivedaysWeatherViewModelFactory(instance(),instance()) }
    }
    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}