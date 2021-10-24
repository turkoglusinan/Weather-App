package com.turkoglu.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.ConsolidatedWeather
import com.google.android.material.button.MaterialButton
import com.turkoglu.weatherapp.Adapters.ForecastAdapter
import com.turkoglu.weatherapp.Adapters.SearchAdapter
import com.turkoglu.weatherapp.Api.Repository
import com.turkoglu.weatherapp.Models.DailyForecastModel
import com.turkoglu.weatherapp.Models.SearchModel
import kotlinx.coroutines.launch


class DetatiledFragment : Fragment() {

    private var woeid: Long? = null
    lateinit var weatherList: MutableList<ConsolidatedWeather>
    lateinit var recyclerView : RecyclerView


    lateinit var titleText: TextView
    lateinit var dateText: TextView
    lateinit var weatherIcon: ImageView
    lateinit var currentTemp: TextView
    lateinit var currentWind: TextView
    lateinit var currentHumidity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        woeid = arguments?.getLong("woeid")
        Log.i("Denemew", woeid.toString())
        lifecycleScope.launch { woeid?.let { getData(it) } }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_detatiled, container, false)


        titleText = view.findViewById(R.id.city_name)
        dateText = view.findViewById(R.id.current_date)
        weatherIcon = view.findViewById(R.id.weather_icon)
        currentTemp = view.findViewById(R.id.current_temp)
        currentWind = view.findViewById(R.id.current_wind)
        currentHumidity = view.findViewById(R.id.current_humidity)

        view.findViewById<MaterialButton>(R.id.back_button).setOnClickListener{
            (requireActivity() as MainActivity).setSearchFragment()
        }


        recyclerView = view.findViewById(R.id.forecast_recycler)

        val myLinearLayoutManager = object : LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerView.layoutManager = myLinearLayoutManager


        return view
    }




    suspend fun getData(query: Long) {

        val repository = Repository()
        val response = woeid?.let { repository.getForecast(it) }
        weatherList = response?.consolidatedWeather as MutableList<ConsolidatedWeather>
        recyclerView.adapter = ForecastAdapter(weatherList, requireContext())
        titleText.setText(response.title)
        dateText.setText(weatherList.first().applicableDate)
        val imageUrl = "https://www.metaweather.com/static/img/weather/png/${weatherList.first().weatherStateAbbr}.png"
        Glide.with(requireContext()).load(imageUrl).into(weatherIcon)
        currentTemp.setText(String.format("%.2f",weatherList.first().theTemp)+" \u2103")
        currentWind.setText(String.format("%.2f",weatherList.first().windSpeed))
        currentHumidity.setText(String.format("%.2f",weatherList.first().humidity)+" %")




        Log.i("Denemew", response.toString())
    }


}