package com.turkoglu.weatherapp.Api

import com.example.example.Example
import com.google.gson.JsonObject
import com.turkoglu.weatherapp.Models.SearchModel
import org.json.JSONArray
import org.json.JSONObject

class Repository {
    suspend fun getPost(query: String): MutableList<SearchModel> {
        return RetrofitInstance.api.getPost(query)

    }

    suspend fun getForecast(): Example {
        return RetrofitInstance.api.getForecast()

    }
}