package com.benallouch.revolut.view.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benallouch.revolut.R
import com.benallouch.revolut.databinding.FragmentRatesBinding
import com.benallouch.revolut.dispatcher.ViewModelFragment
import com.benallouch.revolut.extension.startCoroutineTimer
import com.benallouch.revolut.view.adapter.RatesAdapter
import kotlinx.android.synthetic.main.fragment_rates.*
import org.koin.android.viewmodel.ext.android.getViewModel


class RatesFragment : ViewModelFragment(), RatesFragmentCallBacks {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentRatesBinding>(inflater, R.layout.fragment_rates, container)
            .apply {
                viewModel = getViewModel<RatesFragmentViewModel>().apply {
                    startCoroutineTimer { postRatesCurrency(Pair("EUR", 1.0)) }
                }
                lifecycleOwner = this@RatesFragment
                adapter = RatesAdapter(viewModel!!,this@RatesFragment)
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView_rates.itemAnimator = null
    }

    companion object {
        const val FRAGMENT_TAG = "RATES_FRAGMENT"
    }

    override fun onCurrencyClicked() {
        recyclerView_rates.smoothScrollToPosition(0)
    }

}