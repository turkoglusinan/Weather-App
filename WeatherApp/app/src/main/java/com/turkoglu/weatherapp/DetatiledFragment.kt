package com.turkoglu.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turkoglu.weatherapp.Adapters.ForecastAdapter
import com.turkoglu.weatherapp.Models.DailyForecastModel


class DetatiledFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_detatiled, container, false)


        var recyclerView : RecyclerView
        recyclerView = view.findViewById(R.id.forecast_recycler)

        val myLinearLayoutManager = object : LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerView.layoutManager = myLinearLayoutManager

        recyclerView.adapter = ForecastAdapter(getModels())

        return view
    }

    fun getModels(): MutableList<DailyForecastModel> {

        val models = mutableListOf(
            DailyForecastModel( "Monday", "28th May", 32.0, ""),
            DailyForecastModel( "Tuesday", "31th May", 31.0, ""),
            DailyForecastModel( "Wednesday", "16th July", 30.0, ""),
            DailyForecastModel( "Thursday", "30th December", 29.8, ""),
            DailyForecastModel( "Friday", "28th May", 62.62, ""),

            )
        return models
    }


}