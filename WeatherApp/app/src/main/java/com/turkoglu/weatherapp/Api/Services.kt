package com.turkoglu.weatherapp.Api


import com.example.example.Example
import com.google.gson.JsonObject
import com.turkoglu.weatherapp.Models.SearchModel
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query


interface Services {
    @GET("location/search/")
    suspend fun getPost(@Query("query")query: String): MutableList<SearchModel>

    @GET("location/44418/")
    suspend fun getForecast(): Example

}