package com.benallouch.revolut.view.ui.viewHolder

import android.text.Editable
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.benallouch.revolut.extension.resolveCurrencyRate
import com.benallouch.revolut.extension.resolveCurrencyTitle
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.base.BaseViewHolder
import com.benallouch.revolut.view.ui.rates.AdapterCallBacks
import com.benallouch.revolut.view.ui.rates.toCurrencyDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_rate.view.*
import timber.log.Timber

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

            if (!currencyValue.hasFocus())
                currencyValue.setText(rate.currencyRate.resolveCurrencyRate())
            else {
                Timber.d("Ich bin hier" + rate.currencyCode)
            }

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
        val currencyValueDouble =
            when (currencyValue.isEmpty()) {
                true -> 0.0
                false -> currencyValue.toString().toDouble()
            }

        if (currencyValueDouble != rate.currencyRate) {
            adapterCallBacks.onAdapterAmountChanged(
                Pair(rate.currencyCode, currencyValueDouble)
            )
        }
    }
}

