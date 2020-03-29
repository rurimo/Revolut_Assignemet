package com.benallouch.revolut.extension

import com.benallouch.revolut.models.entity.Rate


fun String.resolveCurrencyTitle(): CharSequence {
    return this.capitalize()
}

fun Map<String, Double>.toRatesList(): List<Rate> {
    return this.entries.map {
        val (k, v) = it
        Rate(k, v)
    }
}

