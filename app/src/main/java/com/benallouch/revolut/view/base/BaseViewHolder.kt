package com.benallouch.revolut.view.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rate.view.*

/** BaseViewHolder is an abstract class for structuring the base view holder class. */
@Suppress("unused", "LeakingThis")
abstract class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener {

    init {
        view.currencyValue.setOnClickListener(this)
        view.setOnClickListener(this)
    }

    @Throws(Exception::class)
    abstract fun bindData(data: Any)

    fun view(): View {
        return view
    }

    fun context(): Context {
        return view.context
    }
}