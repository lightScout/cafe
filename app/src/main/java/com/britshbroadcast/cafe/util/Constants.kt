package com.britshbroadcast.cafe.util

class Constants {
    companion object {
        //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=cafe&keyword=cruise&key=[Your API Key]
        const val BASE_URL = "https://maps.googleapis.com"
        const val API_PATH = "maps/api/place/nearbysearch/json"
        const val API_KEY = "AIzaSyA5rJh9FeteViW7n9M3iORS7d8L7W75wVI"
        const val RADIUS_DISTANCE = "5500"
        const val TYPE_OF = "cafe"

    }
}