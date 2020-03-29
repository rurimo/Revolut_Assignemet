package com.benallouch.revolut.di

import com.benallouch.revolut.view.ui.RatesActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { RatesActivityViewModel(get()) }
}
