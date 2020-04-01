package com.benallouch.revolut.view.ui.viewHolder

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.benallouch.revolut.extension.cancelCoroutineTimer
import com.benallouch.revolut.extension.resolveCurrencyTitle
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.base.BaseViewHolder
import com.benallouch.revolut.view.ui.rates.RatesEventsListener
import com.benallouch.revolut.view.ui.rates.toCurrencyDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_rate.view.*
import timber.log.Timber

class RatesViewHolder(
    val view: View,
    private val ratesEventsListener: RatesEventsListener
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
            //TODO update only the currency value field
            currencyCode.text = rate.currencyCode
            currencyTitle.text = rate.currencyCode.resolveCurrencyTitle()
            Glide.with(context)
                .load(rate.currencyCode.toCurrencyDrawable())
                .apply(RequestOptions().circleCrop())
                .into(currencyIcon)
            currencyValue.setText(rate.currencyRate.toString())

            currencyValue.isFocusableInTouchMode = layoutPosition == 0

        }
    }


    override fun onClick(view: View) {
        if (view == view.currencyValue) {
            //TODO update the adapter separtely here (maybe using doOnTextChanged)
            cancelCoroutineTimer()
            view.currencyValue.doAfterTextChanged {
                if (rate.isMainCurrency) {
                    ratesEventsListener.onAmountChanged(
                        Pair(rate.currencyCode, it.toString().toDouble())
                    )
                }
            }
        } else
            Timber.d("onClick holder")

    }

}


