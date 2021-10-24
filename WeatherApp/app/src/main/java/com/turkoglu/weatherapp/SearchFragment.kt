package com.turkoglu.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turkoglu.weatherapp.Adapters.ForecastAdapter
import com.turkoglu.weatherapp.Adapters.SearchAdapter
import com.turkoglu.weatherapp.Api.Repository
import com.turkoglu.weatherapp.Models.SearchModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)
        var searchView: SearchView = view.findViewById(R.id.search_view)


        recyclerView = view.findViewById(R.id.search_recycler)

        val myLinearLayoutManager =
            object : LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        recyclerView.layoutManager = myLinearLayoutManager



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    lifecycleScope.launch { getData(newText) }
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })


        // Inflate the layout for this fragment
        return view
    }

    suspend fun getData(query: String) {

        val repository = Repository()
        val response = repository.getPost(query)
        var searchlist: MutableList<SearchModel> = response.toMutableList()
        recyclerView.adapter = SearchAdapter(searchlist, requireContext())
        Log.i("Deneme", response.toString())
    }



}