package com.benallouch.revolut.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.adapter.RatesAdapter

@BindingAdapter("adapterRates")
fun bindAdapterPersonList(view: RecyclerView, rates: List<Rate>?) {
    rates?.let { (view.adapter as? RatesAdapter)?.addRates(it) }
}