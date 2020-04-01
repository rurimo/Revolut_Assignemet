package com.benallouch.revolut.extension

import com.benallouch.revolut.models.entity.Rate
import java.text.DecimalFormat
import java.util.*


fun String.resolveCurrencyTitle(): CharSequence {
    return Currency.getInstance(this).displayName
}

fun Map<String, Double>.toRatesList(currencyRate: Double): List<Rate> {
    return this.entries.map {
        val (k, v) = it
        Rate(k, v * currencyRate, false)
    }
}

fun Double.resolveCurrencyRate(): String {
    return DecimalFormat("##.##").format(this)
}


