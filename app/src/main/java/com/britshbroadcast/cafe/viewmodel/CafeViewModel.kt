package com.britshbroadcast.cafe.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.britshbroadcast.cafe.model.data.CafeResult
import com.britshbroadcast.cafe.model.data.Result
import com.britshbroadcast.cafe.model.network.CafeRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CafeViewModel: ViewModel(){

    private val cafeRetrofit = CafeRetrofit()

    val cafeLiveData: MutableLiveData<List<Result>> = MutableLiveData()
    val sizeLiveData: MutableLiveData<Int> = MutableLiveData()

    fun getCafeResult(location: String,){

        cafeRetrofit.getNearByCafes(location)
            .enqueue(object : Callback<CafeResult> {
                override fun onResponse(call: Call<CafeResult>, response: Response<CafeResult>) {
                    response.body()?.let {
                        cafeLiveData.value=it.results

                    } ?: Log.d("TAG_X", "response null ${call.request().url()}")
                }

                override fun onFailure(call: Call<CafeResult>, t: Throwable) {
                    Log.d("TAG_X", "Error ${t.localizedMessage}")
                }

            })

    }

}