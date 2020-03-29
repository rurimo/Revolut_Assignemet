package com.benallouch.revolut.view.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benallouch.revolut.R
import com.benallouch.revolut.databinding.FragmentRatesBinding
import com.benallouch.revolut.dispatcher.ViewModelFragment
import com.benallouch.revolut.view.adapter.RatesAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class RatesFragment : ViewModelFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentRatesBinding>(inflater, R.layout.fragment_rates, container)
            .apply {
                viewModel = getViewModel<RatesFragmentViewModel>().apply { postRatesCurrency("EUR") }
                lifecycleOwner = this@RatesFragment
                adapter = RatesAdapter()
            }.root
    }

    companion object {
        const val FRAGMENT_TAG = "RATES_FRAGMENT"
    }
}