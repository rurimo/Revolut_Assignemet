package com.benallouch.revolut.view.ui.viewHolder

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.benallouch.revolut.extension.resolveCurrencyTitle
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.ui.rates.RatesEventsListener
import com.benallouch.revolut.view.ui.rates.toCurrencyDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_rate.view.*

class RatesViewHolder(val view: View, private val ratesEventsListener: RatesEventsListener) :
    BaseViewHolder(view) {

    private lateinit var rate: Rate
    private var isEditing = false

    override fun bindData(data: Any) {
        if (data is Rate) {
            rate = data
            renderItem()
        }
    }

    private fun renderItem() {
        itemView.run {
            //TODO update omly the currency value field
            //if (!isEditing) {
            currencyCode.text = rate.currencyCode
            currencyTitle.text = rate.currencyCode.resolveCurrencyTitle()
            Glide.with(context)
                .load(rate.currencyCode.toCurrencyDrawable())
                .apply(RequestOptions().circleCrop())
                .into(currencyIcon)
            currencyValue.setText(rate.currencyRate.toString())
            // }

            currencyValue.doAfterTextChanged {
                if (rate.isMainCurrency) {
                    isEditing = true
                    ratesEventsListener.onAmountChanged(
                        Pair(
                            rate.currencyCode,
                            it.toString().toDouble()
                        )
                    )
                }
            }
        }

    }

    override fun onClick(p0: View?) {
    }

    override fun onLongClick(p0: View?): Boolean {
        return false
    }

}


