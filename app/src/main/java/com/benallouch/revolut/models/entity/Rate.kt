package com.benallouch.revolut.models.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rate(val currencyCode: String, val currencyRate: Double) : Parcelable

