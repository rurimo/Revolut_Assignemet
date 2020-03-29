package com.benallouch.revolut

import android.app.Application
import com.benallouch.revolut.di.networkModule
import com.benallouch.revolut.di.repositoryModule
import com.benallouch.revolut.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class RevolutTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RevolutTestApplication)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}