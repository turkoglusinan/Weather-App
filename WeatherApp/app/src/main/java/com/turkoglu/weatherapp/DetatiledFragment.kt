package com.turkoglu.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.turkoglu.weatherapp.Adapters.ForecastAdapter
import com.turkoglu.weatherapp.Adapters.SearchAdapter
import com.turkoglu.weatherapp.Api.Repository
import com.turkoglu.weatherapp.Models.DailyForecastModel
import com.turkoglu.weatherapp.Models.SearchModel
import kotlinx.coroutines.launch


class DetatiledFragment : Fragment() {

    private var woeid: Long? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: Long) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putLong("woeid", param1)
                }
            }
    }


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


        view.findViewById<MaterialButton>(R.id.back_button).setOnClickListener{
            (requireActivity() as MainActivity).setSearchFragment()
        }

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

    suspend fun getData(query: Long) {

        val repository = Repository()
        val response = repository.getForecast()
        Log.i("Denemew", response.toString())
    }


}