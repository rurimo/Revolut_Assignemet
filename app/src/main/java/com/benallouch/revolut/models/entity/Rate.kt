package com.benallouch.revolut.models.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Rate(val currencyCode: String, var currencyRate: Double, var isMainCurrency: Boolean) : Parcelable

