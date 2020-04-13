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

            //NO need to update the editable view that has the focus
            if (!currencyValue.hasFocus())
                currencyValue.setText(rate.currencyRate.resolveCurrencyRate())
        }
    }

    override fun onClick(view: View) {
        when (view == view.currencyValue) {
            true -> {
                //We want to dispatch the changes only when changing the first item in the list (maincurrency)
                if (layoutPosition == 0) {
                    view.currencyValue.doAfterTextChanged { currencyValue ->
                        currencyValue?.let { handleMainCurrencyChange(it) }
                    }
                }
            }
            false -> adapterCallBacks.onCurrencyClicked(layoutPosition,rate)
        }
    }

    private fun handleMainCurrencyChange(currencyValue: Editable) {
        val currencyValueDouble =
            when (currencyValue.isEmpty()) {
                true -> 0.0
                false -> currencyValue.toString().toDouble()
            }

        if (currencyValueDouble != rate.currencyRate) {
            val updatedRate = Rate(rate.currencyCode, currencyValueDouble, rate.isMainCurrency)
            adapterCallBacks.onAdapterAmountChanged(updatedRate)
        }
    }
}

