package com.benallouch.revolut.view.adapter

import android.view.View
import com.benallouch.revolut.R
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.base.BaseAdapter
import com.benallouch.revolut.view.base.SectionRow
import com.benallouch.revolut.view.ui.rates.AdapterCallBacks
import com.benallouch.revolut.view.ui.rates.RatesEventsListener
import com.benallouch.revolut.view.ui.rates.RatesFragmentCallBacks
import com.benallouch.revolut.view.ui.viewHolder.RatesViewHolder


class RatesAdapter(
    private val ratesEventsListener: RatesEventsListener,
    private val fragmentCallBacks: RatesFragmentCallBacks
) : BaseAdapter(),
    AdapterCallBacks {

    init {
        addSection(ArrayList<Rate>())
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_rate
    override fun viewHolder(layout: Int, view: View) =
        RatesViewHolder(view, this)


    fun addRates(rates: List<Rate>) {
        val section = sections()[0]
        section.clear()
        section.addAll(rates)
        notifyItemRangeChanged(0, section.size)
    }

    override fun onAdapterAmountChanged(currencyWithRate: Pair<String, Double>) {
        val section = sections()[0]

        section.map { it as Rate }.filter { !it.isMainCurrency }
            .forEach { it.currencyRate *= currencyWithRate.second }
        notifyItemRangeChanged(1, section.size)

        ratesEventsListener.onAmountChanged(currencyWithRate)
    }

    override fun onCurrencyClicked(rate: Rate) {
        val section = sections()[0]

        (section[0] as Rate).isMainCurrency = false
        rate.isMainCurrency = true

        section.remove(rate)
        section.add(0, rate)
        notifyDataSetChanged()

        fragmentCallBacks.onCurrencyClicked()
        if (rate.isMainCurrency) {
            ratesEventsListener.onAmountChanged(
                Pair(rate.currencyCode, rate.currencyRate.toString().toDouble())
            )
        }
    }

}
