package com.benallouch.revolut.extension

import com.benallouch.revolut.models.entity.Rate
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
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
    val modelVal = BigDecimal(this)
    val displayVal: BigDecimal = modelVal.setScale(2, RoundingMode.HALF_EVEN)
    val priceFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    return priceFormat.format(displayVal.toDouble()).toString()
}


