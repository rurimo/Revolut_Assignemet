package com.benallouch.revolut.di

import com.benallouch.revolut.view.ui.rates.RatesFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { RatesFragmentViewModel(get()) }
}
