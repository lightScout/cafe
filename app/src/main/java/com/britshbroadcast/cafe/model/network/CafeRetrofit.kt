package com.britshbroadcast.cafe.model.network

import com.britshbroadcast.cafe.model.data.CafeResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CafeRetrofit {

    private val cafeService: CafeService = createCafeService(createCafeRetrofit())

    private fun createCafeService(cafeRetrofit: Retrofit): CafeService =
        cafeRetrofit.create(CafeService::class.java)

    private fun createCafeRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getCafeResult(latitude: String,
                      longitude: String,
                      radius: Int,
                      type: String,
                      keyword: String): Call<CafeResult> =
        cafeService.getCafeResult()
}