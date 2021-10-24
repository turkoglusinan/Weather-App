package com.example.example

import com.google.gson.annotations.SerializedName

   
data class Parent (

   @SerializedName("title") var title : String,
   @SerializedName("location_type") var locationType : String,
   @SerializedName("woeid") var woeid : Int,
   @SerializedName("latt_long") var lattLong : String

)