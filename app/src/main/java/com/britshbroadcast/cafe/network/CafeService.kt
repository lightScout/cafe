package com.britshbroadcast.cafe.network

import com.britshbroadcast.cafe.model.data.Result
import com.britshbroadcast.cafe.util.Constants.Companion.API_PATH
import com.britshbroadcast.cafe.util.Constants.Companion.KEY
import com.britshbroadcast.cafe.util.Constants.Companion.KEYWORD
import com.britshbroadcast.cafe.util.Constants.Companion.LOCATION
import com.britshbroadcast.cafe.util.Constants.Companion.RADIUS
import com.britshbroadcast.cafe.util.Constants.Companion.TYPE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CafeService {
    /*https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=cafe&keyword=cruise&key=[Your API Key]
    *const val BASE_URL = "https://maps.googleapis.com"
        const val API_PATH = "/maps/api/place/nearbysearch/json"
        const val LOCATION = "location"
        const val FROM = "from"
        const val QUERY = "popularity"
        const val API_KEY = "AIzaSyA5rJh9FeteViW7n9M3iORS7d8L7W75wVI"
        const val KEY = "key"
        const val RADIUS = "radius"
        const val TYPE = "type"
        const val KEYWORD = "keyword"
        const val LOCATION_LATITUDE = "-33.8670522,151.1957362"
        const val RADIUS_DISTANCE = "1500"
        const val TYPE_OF = "cafe"
        const val CRUISE = "cruise"

     */
    //@GET(API_PATH)
    //fun getCafe(@Query(LOCATION) location: String, @Query(RADIUS) radius: Int, @Query(TYPE) type: String, @Query(KEYWORD) keyword: String, @Query(KEY) key: String) : Call<Result>

    //fun getNearByCafes(@Query(LOCATION) location: String,   @Query(RADIUS) radius: String, @Query(TYPE) type: String, @Query(KEYWORD) keyword: String, @Query(KEY) key: String) : Call<Result>

    @GET("maps/api/place/nearbysearch/json")
    fun getNearByCafes(@Query("location")location: String, @Query("radius") radius: Int, @Query("type") type: String, @Query("key") apiKey: String) : Call<Result>

}