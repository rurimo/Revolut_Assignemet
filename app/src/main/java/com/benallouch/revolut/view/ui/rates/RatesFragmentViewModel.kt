package com.benallouch.revolut.view.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.benallouch.revolut.dispatcher.DispatchViewModel
import com.benallouch.revolut.extension.startCoroutineTimer
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.repository.RatesRepository
import timber.log.Timber

class RatesFragmentViewModel constructor(private val ratesRepository: RatesRepository) :
    DispatchViewModel(), RatesEventsListener {

    private val baseCurrencyLiveData: MutableLiveData<Pair<String, Double>> = MutableLiveData()
    val ratesListLiveData: LiveData<List<Rate>>

    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection RatesActivityViewModel")

        this.ratesListLiveData = baseCurrencyLiveData.switchMap { baseCurrencyWithRate ->
            launchOnViewModelScope {
                ratesRepository.loadRates(baseCurrencyWithRate) { toastLiveData.postValue(it) }
            }
        }
    }

    fun postRatesCurrency(currencyWithRate: Pair<String, Double>) =
        baseCurrencyLiveData.postValue(currencyWithRate)

    override fun onAmountChanged(currencyWithRate: Pair<String, Double>) {
            startCoroutineTimer { postRatesCurrency(currencyWithRate) }
    }

}