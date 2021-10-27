package com.turkoglu.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.turkoglu.weatherapp.Adapters.LocationAdapter
import com.turkoglu.weatherapp.Adapters.SearchAdapter
import com.turkoglu.weatherapp.Api.Repository
import com.turkoglu.weatherapp.Models.LocationModel
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

        //fetchLocation()


        view.findViewById<MaterialButton>(R.id.location_button).setOnClickListener {
            //fetchLocation()
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )

                    // REQUEST_CODE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                Log.i("Denemew", "çalıştı")
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        Log.i("Denemew", location?.longitude.toString())
                        var locationLatLong =
                            location?.latitude.toString() + "," + location?.longitude.toString()
                        Log.i("Denemew", locationLatLong)
                        lifecycleScope.launch { getLocationData(locationLatLong) }


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


    suspend fun getLocationData(query: String) {

        val repository = Repository()
        val response = repository.getLatlon(query)
        var locationList: MutableList<LocationModel> = response.toMutableList()
        lateinit var searchList: MutableList<LocationModel>

        locationList.forEach { item ->
            val model = LocationModel(item.distance, item.title, item.location_type, item.woeid, item.latt_long)
            searchList.add(model)

        }

        recyclerView.adapter = LocationAdapter(searchList, requireContext())
    }




    /*private fun fetchLocation() {
        val task = fusedLocationClient.lastLocation

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }else{
            getlocations()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getlocations() {
        fusedLocationClient.lastLocation?.addOnSuccessListener {
            if (it == null){
                Toast.makeText(requireContext(), "Sorry Can't get Location", Toast.LENGTH_SHORT).show()
            }else it.apply {
                val latitude = it.latitude
                val longitude = it.longitude
                Toast.makeText(requireContext(), "${latitude} ${longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                    getlocations()
                }
                else{
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }*/


}