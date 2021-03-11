package com.britshbroadcast.cafe.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.britshbroadcast.cafe.R
import com.britshbroadcast.cafe.databinding.ActivityMainBinding
import com.britshbroadcast.cafe.view.adapter.CafeItemAdapter
import com.britshbroadcast.cafe.viewmodel.CafeViewModel

class MainActivity : AppCompatActivity(), LocationListener {

    private val REQUEST_CODE = 222

    private lateinit var  locationManager: LocationManager

    private val cafeViewModel by viewModels<CafeViewModel>()

    private val cafeItemAdapter = CafeItemAdapter(mutableListOf())

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationManager =  getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val snapHelper: SnapHelper=LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.mainRecyclerView)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.mainRecyclerView.adapter = cafeItemAdapter


    }


    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    private fun checkPermission() {
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED){
            registerLocationListener()
    }else{
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE && permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerLocationListener()

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )) {
                requestPermission()
            } else {
                    // showOverlay()
            }
        }


    }
    }


    @SuppressLint("MissingPermission")
    private fun registerLocationListener() {
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            2000L,
            0f,
            this
        )

    }

    override fun onLocationChanged(location: Location) {



        cafeViewModel.cafeLiveData.observe(this, Observer { it ->

            binding.pagerTextView.text=getString(R.string.pager_text, it.size.toString())
            cafeItemAdapter.updateDate(it)
            binding.loadingTexView.visibility = View.GONE

        })

      cafeViewModel.getCafeResult("${location.latitude},${location.longitude}")
    }

}