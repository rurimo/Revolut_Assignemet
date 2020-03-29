package com.benallouch.revolut.api.client

import com.benallouch.revolut.api.ApiResponse
import com.benallouch.revolut.api.async
import com.benallouch.revolut.api.service.RatesService
import com.benallouch.revolut.models.network.RatesListResponse

class RatesClient(private val ratesService: RatesService) {
    fun getLatestRates(
        query: String,
        onResult: (response: ApiResponse<RatesListResponse>) -> Unit
    ) {
        this.ratesService.fetchLatestRates(query).async(onResult)
    }
}