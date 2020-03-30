package com.benallouch.revolut.view.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator
import com.benallouch.revolut.R
import com.benallouch.revolut.databinding.FragmentRatesBinding
import com.benallouch.revolut.dispatcher.ViewModelFragment
import com.benallouch.revolut.extension.startCoroutineTimer
import com.benallouch.revolut.view.adapter.RatesAdapter
import kotlinx.android.synthetic.main.fragment_rates.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.getViewModel


class RatesFragment : ViewModelFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentRatesBinding>(inflater, R.layout.fragment_rates, container)
            .apply {
                viewModel = getViewModel<RatesFragmentViewModel>().apply {
                    startCoroutineTimer {
                        postRatesCurrency("EUR")
                    }
                }
                lifecycleOwner = this@RatesFragment
                adapter = RatesAdapter()
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (recyclerView_rates.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    companion object {
        const val FRAGMENT_TAG = "RATES_FRAGMENT"
    }
}