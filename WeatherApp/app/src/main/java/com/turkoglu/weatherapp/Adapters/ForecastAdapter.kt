package com.turkoglu.weatherapp.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.ConsolidatedWeather
import com.turkoglu.weatherapp.Models.DailyForecastModel
import com.turkoglu.weatherapp.R
import java.text.SimpleDateFormat

class ForecastAdapter(val forecastList: MutableList<ConsolidatedWeather>, var context: Context) : RecyclerView.Adapter<ForecastAdapter.ModelViewHolder>() {

    var mContext = context
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateText: TextView = view.findViewById(R.id.date_textview)
        val tempText: TextView = view.findViewById(R.id.temp_textview)
        val weatherText: TextView = view.findViewById(R.id.weather_state)
        val imageView: ImageView = view.findViewById(R.id.icon_imageview)

        fun bindItems(item: ConsolidatedWeather, context: Context) {

            val imageUrl = "https://www.metaweather.com/static/img/weather/png/64/${item.weatherStateAbbr}.png"
            Glide.with(context).load(imageUrl).into(imageView)

            //Log.i("denemew", imageUrl)

            dateText.setText(item.applicableDate)
            tempText.setText(String.format("%.2f",item.theTemp)+" \u2103")
            weatherText.setText(item.weatherStateName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(forecastList.get(position),mContext)
    }
}