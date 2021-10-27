package com.turkoglu.weatherapp.Api


import com.example.example.Example
import com.google.gson.JsonObject
import com.turkoglu.weatherapp.Models.LocationModel
import com.turkoglu.weatherapp.Models.SearchModel
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Services {
    @GET("location/search/")
    suspend fun getPost(@Query("query")query: String): MutableList<SearchModel>

    @GET("location/{id}/")
    suspend fun getForecast(@Path("id") id: Long): Example

    @GET("location/search/")
    suspend fun getLatlon(@Query("lattlong") lattlong: String): MutableList<LocationModel>

}