package com.turkoglu.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import java.net.InetAddress

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setSearchFragment()
        }


    }

    fun setSearchFragment(){
        val fragment = SearchFragment()
        replaceFragment(fragment)
    }

    fun setDetailedFragment(woeid : Long){
        val myFragment = DetatiledFragment()
        val bundle = Bundle()
        bundle.putLong("woeid",woeid)
        myFragment.arguments = bundle
        replaceFragment(myFragment)

    }

    fun AppCompatActivity.replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}