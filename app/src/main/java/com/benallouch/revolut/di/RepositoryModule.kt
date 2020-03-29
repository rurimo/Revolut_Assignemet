package com.benallouch.revolut.di

import com.benallouch.revolut.repository.RatesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { RatesRepository(get()) }

}