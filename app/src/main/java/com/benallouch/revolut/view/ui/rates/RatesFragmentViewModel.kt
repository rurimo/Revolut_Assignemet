package com.benallouch.revolut.view.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.benallouch.revolut.dispatcher.DispatchViewModel
import com.benallouch.revolut.models.entity.Rate
import com.benallouch.revolut.repository.RatesRepository
import timber.log.Timber

class RatesFragmentViewModel constructor(private val ratesRepository: RatesRepository) :
    DispatchViewModel() {

    private val baseCurrencyLiveData: MutableLiveData<String> = MutableLiveData()
    val ratesListLiveData: LiveData<List<Rate>>

    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("injection RatesActivityViewModel")

        this.ratesListLiveData = baseCurrencyLiveData.switchMap { baseCurrency ->
            launchOnViewModelScope {
                ratesRepository.loadRates(baseCurrency) { toastLiveData.postValue(it) }
            }
        }
    }

    fun postRatesCurrency(baseCurrency: String) = baseCurrencyLiveData.postValue(baseCurrency)
}