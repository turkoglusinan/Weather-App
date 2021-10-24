package com.example.example

import com.google.gson.annotations.SerializedName

   
data class ConsolidatedWeather (

   @SerializedName("id") var id : Long,
   @SerializedName("weather_state_name") var weatherStateName : String,
   @SerializedName("weather_state_abbr") var weatherStateAbbr : String,
   @SerializedName("wind_direction_compass") var windDirectionCompass : String,
   @SerializedName("created") var created : String,
   @SerializedName("applicable_date") var applicableDate : String,
   @SerializedName("min_temp") var minTemp : Double,
   @SerializedName("max_temp") var maxTemp : Double,
   @SerializedName("the_temp") var theTemp : Double,
   @SerializedName("wind_speed") var windSpeed : Double,
   @SerializedName("wind_direction") var windDirection : Double,
   @SerializedName("air_pressure") var airPressure : Double,
   @SerializedName("humidity") var humidity : Double,
   @SerializedName("visibility") var visibility : Double,
   @SerializedName("predictability") var predictability : Double

)