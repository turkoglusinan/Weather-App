package com.turkoglu.weatherapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turkoglu.weatherapp.Models.DailyForecastModel
import com.turkoglu.weatherapp.R

class ForecastAdapter(val forecastList: MutableList<DailyForecastModel>) : RecyclerView.Adapter<ForecastAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayText: TextView = view.findViewById(R.id.day_textview)
        val dateText: TextView = view.findViewById(R.id.date_textview)
        val tempText: TextView = view.findViewById(R.id.temp_textview)

        fun bindItems(item: DailyForecastModel) {
            dayText.setText(item.day)
            dateText.setText(item.date)
            tempText.setText(item.tempereture.toString())
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
        holder.bindItems(forecastList.get(position))
    }
}