package com.benallouch.revolut.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.adapter.RatesAdapter

@BindingAdapter("adapterRates")
fun bindAdapterRatesList(view: RecyclerView, rates: List<Rate>?) {
    if (!rates.isNullOrEmpty()) { (view.adapter as RatesAdapter).updateRatesList(rates)
    }
}