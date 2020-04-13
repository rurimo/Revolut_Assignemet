package com.benallouch.revolut.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.benallouch.revolut.models.entity.Rate
import javax.annotation.Nullable

class RatesDiffCallback(
    var newRates: List<Rate>,
    var oldRates: List<Rate>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldRates.size
    }

    override fun getNewListSize(): Int {
        return newRates.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return (oldRates[oldItemPosition].currencyCode == newRates[newItemPosition].currencyCode)
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldRates[oldItemPosition] == newRates[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Any? { // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}