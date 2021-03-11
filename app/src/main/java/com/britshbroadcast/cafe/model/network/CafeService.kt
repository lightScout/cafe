package com.britshbroadcast.cafe.model.network

import com.britshbroadcast.cafe.model.data.CafeResult
import com.britshbroadcast.cafe.util.Constants.Companion.API_PATH
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CafeService {


    @GET(API_PATH)
    fun getNearByCafes(@Query("location")location: String, @Query("radius") radius: String, @Query("type") type: String, @Query("key") apiKey: String) : Call<CafeResult>

}