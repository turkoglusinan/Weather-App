package com.turkoglu.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.button.MaterialButton
import com.turkoglu.weatherapp.Adapters.ForecastAdapter
import com.turkoglu.weatherapp.Adapters.SearchAdapter
import com.turkoglu.weatherapp.Api.Repository
import com.turkoglu.weatherapp.Models.SearchModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    private lateinit var fusedLocationClient: FusedLocationProviderClient



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


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


        view.findViewById<MaterialButton>(R.id.location_button).setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        1)

                    // REQUEST_CODE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                Log.i("Denemew", "çalıştı")
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location? ->
                        Log.i("Denemew", location.toString())
                    }
            }

        }


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