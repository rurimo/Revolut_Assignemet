package com.benallouch.revolut.models.network

import com.benallouch.revolut.models.entity.Rate

data class RatesListResponse(
    val baseCurrency: String,
    val rates: Map<String, Double>
)
