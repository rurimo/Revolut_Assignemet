package com.benallouch.revolut.view.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benallouch.revolut.R
import com.benallouch.revolut.databinding.FragmentRatesBinding
import com.benallouch.revolut.dispatcher.ViewModelFragment
import com.benallouch.revolut.extension.startCoroutineTimer
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.view.adapter.RatesAdapter
import kotlinx.android.synthetic.main.fragment_rates.*
import org.koin.android.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.concurrent.schedule


class RatesFragment : ViewModelFragment(), RatesFragmentCallBacks {

    private var recyclerViewAnimator: RecyclerView.ItemAnimator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentRatesBinding>(inflater, R.layout.fragment_rates, container)
            .apply {
                viewModel = getViewModel<RatesFragmentViewModel>().apply {
                    startCoroutineTimer { postRatesCurrency(Rate("EUR", 1.0, true)) }
                }
                lifecycleOwner = this@RatesFragment
                adapter = RatesAdapter(viewModel!!, this@RatesFragment)
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewAnimator = recyclerView_rates.itemAnimator
        recyclerView_rates.itemAnimator = null
    }

    override fun onCurrencyClicked() {
        recyclerView_rates.itemAnimator = recyclerViewAnimator
        getViewModel<RatesFragmentViewModel>().apply {
            unsubscribe(this@RatesFragment)
        }
    }

    override fun onAdapterItemMoved(fetchCurrencies: () -> Unit) {
        Timer().schedule(300) {
            activity!!.runOnUiThread {
                recyclerView_rates.itemAnimator = null
                fetchCurrencies.invoke()
            }
        }
    }

    override fun scrollToPosition(position: Int) {
        Timer().schedule(300) {
            recyclerView_rates.smoothScrollToPosition(position)
        }
    }

    companion object {
        const val FRAGMENT_TAG = "RATES_FRAGMENT"
    }
}