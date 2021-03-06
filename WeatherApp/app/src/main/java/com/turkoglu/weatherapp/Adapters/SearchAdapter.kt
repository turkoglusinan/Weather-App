package com.turkoglu.weatherapp.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.turkoglu.weatherapp.MainActivity
import com.turkoglu.weatherapp.Models.SearchModel
import com.turkoglu.weatherapp.R

class SearchAdapter(val searchList: MutableList<SearchModel>, var context: Context) : RecyclerView.Adapter<SearchAdapter.ModelViewHolder>() {

    var mContext = context

    class ModelViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val searchTitle: TextView = view.findViewById(R.id.search_title)


        fun bindItems(item: SearchModel, context: Context) {
            searchTitle.setText(item.title)
            var layout = view.findViewById<ConstraintLayout>(R.id.search_item_layout)
            layout.setOnClickListener{
                (context as MainActivity).setDetailedFragment(item.woeid)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
       /* view.setOnClickListener{
            (mContext as MainActivity).setDetailedFragment()

        }*/


        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(searchList.get(position), mContext)

    }
}