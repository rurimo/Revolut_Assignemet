package com.benallouch.revolut.repository

import androidx.lifecycle.MutableLiveData
import com.benallouch.revolut.api.ApiResponse
import com.benallouch.revolut.api.client.RatesClient
import com.benallouch.revolut.api.message
import com.benallouch.revolut.extension.toRatesList
import com.benallouch.revolut.models.entity.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RatesRepository constructor(private val ratesClient: RatesClient) {

    init {
        Timber.d("Injection RatesRepository")
    }

    suspend fun loadRates(currencyWithRate: Pair<String,Double>,  error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<List<Rate>>()
            var rates = arrayListOf<Rate>()
            ratesClient.getLatestRates(currencyWithRate.first) { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let { data ->
                            rates.add(Rate(currencyWithRate.first, currencyWithRate.second,true))
                            rates.addAll(data.rates.toRatesList(currencyWithRate.second) as ArrayList<Rate>)
                            liveData.postValue(rates)
                        }
                    }
                    is ApiResponse.Failure.Error -> error(response.message())
                    is ApiResponse.Failure.Exception -> error(response.message())
                }
            }
            liveData.apply { postValue(rates) }

        }

}