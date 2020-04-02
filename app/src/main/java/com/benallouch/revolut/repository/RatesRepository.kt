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

    suspend fun loadRates(currencyWithRate: Rate, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<List<Rate>>()
            var rates = arrayListOf<Rate>()
            ratesClient.getLatestRates(currencyWithRate.currencyCode) { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let { data ->
                            rates.add(
                                Rate(
                                    currencyWithRate.currencyCode,
                                    currencyWithRate.currencyRate,
                                    true
                                )
                            )
                            rates.addAll(data.rates.toRatesList(currencyWithRate.currencyRate) as ArrayList<Rate>)
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