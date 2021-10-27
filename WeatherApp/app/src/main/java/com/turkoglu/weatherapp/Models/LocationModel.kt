package com.turkoglu.weatherapp.Models

data class LocationModel(

    val distance: Long,
    val title: String,
    val location_type: String,
    val woeid: Long,
    val latt_long: String

)

