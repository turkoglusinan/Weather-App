package com.example.example

import com.google.gson.annotations.SerializedName

   
data class Example (

   @SerializedName("consolidated_weather") var consolidatedWeather : List<ConsolidatedWeather>,
   @SerializedName("time") var time : String,
   @SerializedName("sun_rise") var sunRise : String,
   @SerializedName("sun_set") var sunSet : String,
   @SerializedName("timezone_name") var timezoneName : String,
   @SerializedName("parent") var parent : Parent,
   @SerializedName("sources") var sources : List<Sources>,
   @SerializedName("title") var title : String,
   @SerializedName("location_type") var locationType : String,
   @SerializedName("woeid") var woeid : Int,
   @SerializedName("latt_long") var lattLong : String,
   @SerializedName("timezone") var timezone : String

)