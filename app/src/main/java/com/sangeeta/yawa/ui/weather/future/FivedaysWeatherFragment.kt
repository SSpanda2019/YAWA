package com.sangeeta.yawa.ui.weather.future

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.sangeeta.yawa.R
import com.sangeeta.yawa.data.network.response.nextfivedays.X
import com.sangeeta.yawa.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fivedays_weather_fragment.*
import kotlinx.android.synthetic.main.todays_weather_fragment.*
import kotlinx.android.synthetic.main.todays_weather_fragment.group_loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FivedaysWeatherFragment : ScopedFragment(), KodeinAware {


    override val kodein by closestKodein()
    private val viewModelFactory: FivedaysWeatherViewModelFactory by instance()


    private lateinit var viewModel: FivedaysWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fivedays_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FivedaysWeatherViewModel::class.java)

        bindUI()
    }


    private fun bindUI() = launch(Dispatchers.Main) {
        val futureWeatherEntries = viewModel.weather.await()

        futureWeatherEntries.observe(this@FivedaysWeatherFragment, Observer { weatherEntries ->

            if (weatherEntries == null)
                return@Observer
            group_loading.visibility = View.GONE
            updateLocation(weatherEntries.city.name)
            updateDateToNextWeek()
            initRecyclerView(weatherEntries.list.toFutureWeatherItems())
        })
    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToNextWeek() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Next 5 days"
    }


    private fun List<X>.toFutureWeatherItems() : List<FiveDaysWeatherItem> {
        return this.map {
            FiveDaysWeatherItem(it)
        }
    }
    private fun initRecyclerView(items: List<FiveDaysWeatherItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FivedaysWeatherFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->

            Toast.makeText(this@FivedaysWeatherFragment.context,"item clicked", Toast.LENGTH_SHORT).show()
        }
    }

}
