package com.benallouch.revolut.view.ui.rates

import com.benallouch.revolut.models.entity.Rate

interface RatesEventsListener {
    fun onAmountChanged(currencyWithRate: Pair<String, Double>)
    fun onCurrencyClicked(rate: Rate)
}