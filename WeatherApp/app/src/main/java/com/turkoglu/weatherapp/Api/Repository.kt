package com.turkoglu.weatherapp.Api

import com.example.example.Example
import com.google.gson.JsonObject
import com.turkoglu.weatherapp.Models.LocationModel
import com.turkoglu.weatherapp.Models.SearchModel
import org.json.JSONArray
import org.json.JSONObject

class Repository {
    suspend fun getPost(query: String): MutableList<SearchModel> {
        return RetrofitInstance.api.getPost(query)

    }

    suspend fun getForecast(woeid: Long): Example {
        return RetrofitInstance.api.getForecast(woeid)

    }

    suspend fun getLatlon(lattlong: String): MutableList<LocationModel> {
        return RetrofitInstance.api.getLatlon(lattlong)

    }
}