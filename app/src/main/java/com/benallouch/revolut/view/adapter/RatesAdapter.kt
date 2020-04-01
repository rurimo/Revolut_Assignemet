package com.benallouch.revolut.view.adapter

import android.view.View
import com.benallouch.revolut.R
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.base.BaseAdapter
import com.benallouch.revolut.view.base.SectionRow
import com.benallouch.revolut.view.ui.rates.RatesEventsListener
import com.benallouch.revolut.view.ui.viewHolder.RatesViewHolder

class RatesAdapter(private val ratesEventsListener: RatesEventsListener) : BaseAdapter() {

    init {
        addSection(ArrayList<Rate>())
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_rate
    override fun viewHolder(layout: Int, view: View) = RatesViewHolder(view, ratesEventsListener)

    fun addRates(rates: List<Rate>) {
        val section = sections()[0]
        section.clear()
        section.addAll(rates)
        notifyItemRangeChanged(0, section.size)
    }

}