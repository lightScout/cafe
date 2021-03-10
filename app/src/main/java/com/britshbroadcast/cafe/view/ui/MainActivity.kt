package com.britshbroadcast.cafe.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.britshbroadcast.cafe.R
import com.britshbroadcast.cafe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LocationListener {

    private val REQUEST_CODE = 222

    private lateinit var  locationManager: LocationManager

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationManager =  getSystemService(Context.LOCATION_SERVICE) as LocationManager


    }


    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    private fun checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            registerLocationListener()
    }else{
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE && permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerLocationListener()

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
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
        binding.locationTextView.text = getString(R.string.location_string, location.latitude, location.longitude)

    }

}