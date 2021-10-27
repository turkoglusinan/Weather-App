package com.turkoglu.weatherapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.turkoglu.weatherapp.MainActivity
import com.turkoglu.weatherapp.Models.LocationModel
import com.turkoglu.weatherapp.R

class LocationAdapter(val locationList: MutableList<LocationModel>, var context: Context) : RecyclerView.Adapter<LocationAdapter.ModelViewHolder>() {

    var mContext = context

    class ModelViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val locationTitle: TextView = view.findViewById(R.id.search_title)
        val locationDistance: TextView = view.findViewById(R.id.show_distance)



        fun bindItems(item: LocationModel, context: Context) {
            locationTitle.setText(item.title)
            locationDistance.setText(item.distance.toString())
            var layout = view.findViewById<ConstraintLayout>(R.id.location_item_layout)
            layout.setOnClickListener{
                (context as MainActivity).setDetailedFragment(item.woeid)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)



        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(locationList.get(position), mContext)

    }
}