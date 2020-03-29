package com.benallouch.revolut.view.ui.rates

import com.benallouch.revolut.R


    fun String.toCurrencyDrawable(): Int {
        return when (this) {
            "AUD" -> R.drawable.aud

            "BGN" -> R.drawable.bgn

            "BRL" -> R.drawable.brl

            "CAD" -> R.drawable.cad

            "CHF" -> R.drawable.chf

            "CNY" -> R.drawable.cny

            "CZK" -> R.drawable.czk

            "DKK" -> R.drawable.dkk

            "EUR" -> R.drawable.eur

            "GBP" -> R.drawable.gbp

            "HKD" -> R.drawable.hkd

            "HRK" -> R.drawable.hrk

            "HUF" -> R.drawable.huf

            "IDR" -> R.drawable.idr

            "ILS" -> R.drawable.ils

            "INR" -> R.drawable.inr

            "ISK" -> R.drawable.isk

            "JPY" -> R.drawable.jpy

            "KRW" -> R.drawable.krw

            "MXN" -> R.drawable.mxn

            "MYR" -> R.drawable.myr

            "NOK" -> R.drawable.nok

            "NZD" -> R.drawable.nzd

            "PHP" -> R.drawable.php

            "PLN" -> R.drawable.pln

            "RON" -> R.drawable.ron

            "RUB" -> R.drawable.rub

            "SEK" -> R.drawable.sek

            "SGD" -> R.drawable.sgd

            "THB" -> R.drawable.thb

            "USD" -> R.drawable.usd

            "ZAR" -> R.drawable.zar

            else -> R.drawable.error
        }
    }
