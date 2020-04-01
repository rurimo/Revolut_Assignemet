package com.benallouch.revolut.view.ui.viewHolder

import android.text.Editable
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.benallouch.revolut.extension.cancelCoroutineTimer
import com.benallouch.revolut.extension.resolveCurrencyRate
import com.benallouch.revolut.extension.resolveCurrencyTitle
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.base.BaseViewHolder
import com.benallouch.revolut.view.ui.rates.AdapterCallBacks
import com.benallouch.revolut.view.ui.rates.RatesEventsListener
import com.benallouch.revolut.view.ui.rates.toCurrencyDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_rate.view.*

class RatesViewHolder(
    val view: View,
    private val adapterCallBacks: AdapterCallBacks
) :
    BaseViewHolder(view) {

    private lateinit var rate: Rate

    override fun bindData(data: Any) {
        if (data is Rate) {
            rate = data
            renderItem()
        }
    }

    private fun renderItem() {
        itemView.run {
            currencyCode.text = rate.currencyCode
            currencyTitle.text = rate.currencyCode.resolveCurrencyTitle()
            currencyValue.isFocusableInTouchMode = layoutPosition == 0
            Glide.with(context)
                .load(rate.currencyCode.toCurrencyDrawable())
                .apply(RequestOptions().circleCrop())
                .into(currencyIcon)
            currencyValue.setText(rate.currencyRate.resolveCurrencyRate())

        }
    }

    override fun onClick(view: View) {
        when (view == view.currencyValue) {
            true -> {
                if (rate.isMainCurrency) {
                   // cancelCoroutineTimer()
                    view.currencyValue.doAfterTextChanged { currencyValue ->
                        currencyValue?.let { handleMainCurrencyChange(it) }
                    }
                }
            }
            false -> adapterCallBacks.onCurrencyClicked(rate)
        }
    }

    private fun handleMainCurrencyChange(currencyValue: Editable) {
        if (currencyValue.toString().toDouble() != rate.currencyRate) {
            adapterCallBacks.onAdapterAmountChanged(
                Pair(rate.currencyCode, currencyValue.toString().toDouble())
            )
        }
    }
}


/*
                       if (it.isEmpty()) {
                           ratesEventsListener.onAmountChanged(
                               Pair(rate.currencyCode, 0.0)
                           )
                       } else {
                           ratesEventsListener.onAmountChanged(
                               Pair(rate.currencyCode, it.toString().toDouble())
                           )
                       }*/
