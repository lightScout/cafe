package com.britshbroadcast.cafe.network

import com.britshbroadcast.cafe.util.Constants.Companion.API_KEY
import com.britshbroadcast.cafe.util.Constants.Companion.BASE_URL
import com.britshbroadcast.cafe.util.Constants.Companion.LOCATION_LATITUDE
import com.britshbroadcast.cafe.util.Constants.Companion.RADIUS_DISTANCE
import com.britshbroadcast.cafe.util.Constants.Companion.TYPE_OF
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CafeRetrofit {

    private val cafeService: CafeService

    init {
        cafeService = createCafeService(createCafeRetrofit())
    }

    private fun createCafeRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun createCafeService(retrofit: Retrofit): CafeService = retrofit.create(CafeService::class.java)
    //private fun createCafeService(retrofit: Retrofit) = retrofit.create(CafeService::class.java)

    fun getNearByCafes(location: String, radius: Int, type: String, apiKey: String ): Call<Result> = cafeService.getNearByCafes(LOCATION_LATITUDE, RADIUS_DISTANCE, TYPE_OF, API_KEY)
}