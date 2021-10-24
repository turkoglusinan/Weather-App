package com.turkoglu.weatherapp.Api

import com.turkoglu.weatherapp.Api.WeatherApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Services by lazy {
        retrofit.create(Services::class.java)
    }
}