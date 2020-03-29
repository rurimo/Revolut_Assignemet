package com.benallouch.revolut.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benallouch.revolut.R
import com.benallouch.revolut.view.ui.rates.RatesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
    }

    private fun initializeUI() {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragmentHolder, RatesFragment(), RatesFragment.FRAGMENT_TAG)
            commit()
        }
    }
}