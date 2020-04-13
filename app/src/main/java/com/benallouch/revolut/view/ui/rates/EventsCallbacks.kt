package com.benallouch.revolut.view.ui.rates

import com.benallouch.revolut.models.entity.Rate

/**
 * We define the callbacks that send the date from the view holder to different components
 *
 */

//Once change the amount we send the event from the Adapter to the viewModel to fetch the currencies based on the new value
interface ViewModelCallbacks {
    fun onAmountChanged(currencyWithRate: Rate)
}

//The viewHolder informs the adapter using these callbacks
interface AdapterCallBacks {
    fun onAdapterAmountChanged(currencyWithRate: Rate)
    fun onCurrencyClicked(fromPosition: Int, rate: Rate)

}

//The adapter informs the fragment using this callbacks in order to scroll the view to the top
interface RatesFragmentCallBacks {
    fun onCurrencyClicked()
    fun onAdapterItemMoved(function: () -> Unit)
    fun scrollToPosition(position: Int)
}