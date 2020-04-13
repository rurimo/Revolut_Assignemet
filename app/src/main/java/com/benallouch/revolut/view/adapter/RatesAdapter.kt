package com.benallouch.revolut.view.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.benallouch.revolut.R
import com.benallouch.revolut.extension.cancelCoroutineTimer
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.base.BaseAdapter
import com.benallouch.revolut.view.base.SectionRow
import com.benallouch.revolut.view.ui.rates.AdapterCallBacks
import com.benallouch.revolut.view.ui.rates.RatesFragmentCallBacks
import com.benallouch.revolut.view.ui.rates.ViewModelCallbacks
import com.benallouch.revolut.view.ui.viewHolder.RatesViewHolder


class RatesAdapter(
    private val viewModelCallbacks: ViewModelCallbacks,
    private val fragmentCallBacks: RatesFragmentCallBacks
) : BaseAdapter(), AdapterCallBacks {

    private lateinit var currencyView: RecyclerView

    init {
        addSection(ArrayList<Rate>())
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_rate
    override fun viewHolder(layout: Int, view: View): RatesViewHolder {
        return RatesViewHolder(view, this)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        currencyView = recyclerView
    }

    fun addRates(rates: List<Rate>) {
        val section = sections()[0]
        section.clear()
        section.addAll(rates)
        notifyItemRangeChanged(0, section.size)
    }

    override fun onAdapterAmountChanged(currencyWithRate: Rate) {
        if (currencyWithRate.isMainCurrency) {
            val section = sections()[0]
            section.map { it as Rate }.filter { !it.isMainCurrency }
                .forEach { it.currencyRate *= currencyWithRate.currencyRate }

            notifyItemRangeChanged(1, section.size)
            viewModelCallbacks.onAmountChanged(currencyWithRate)
        }
    }

    override fun onCurrencyClicked(fromPosition: Int, rate: Rate) {
        fragmentCallBacks.onCurrencyClicked()
        cancelCoroutineTimer()

        val section = sections()[0]
        (section[0] as Rate).isMainCurrency = false
        rate.isMainCurrency = true

        section.remove(rate)
        section.add(0, rate)
        notifyItemMoved(fromPosition, 0)

        fragmentCallBacks.onAdapterItemMoved {
            if (rate.isMainCurrency) {
                viewModelCallbacks.onAmountChanged(rate)
                fragmentCallBacks.scrollToPosition(0)

            }
        }
    }

    fun updateRatesList(rates: List<Rate>) {
        val diffCallback = RatesDiffCallback(sections()[0] as List<Rate>, rates)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        sections()[0].clear()
        sections()[0].addAll(rates)
        diffResult.dispatchUpdatesTo(this)
    }
}
