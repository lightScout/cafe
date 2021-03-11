package com.britshbroadcast.cafe.model.network

import retrofit2.http.GET

interface CafeService {
    @GET()
    fun getCafeResult()
}