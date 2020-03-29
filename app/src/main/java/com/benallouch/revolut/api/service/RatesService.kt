package com.benallouch.revolut.api.service

import com.benallouch.revolut.models.network.RatesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {

    @GET("api/android/latest")
    fun getLatestRates(@Query("base") baseCurrency: String): Call<RatesListResponse>
}