package com.benallouch.revolut.api

import com.benallouch.revolut.api.service.RatesService
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RatesServiceTest : ApiAbstract<RatesService>() {
    private lateinit var service: RatesService

    @Before
    fun initService() {
        this.service = createService(RatesService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun getLatestRatesTest() {
        enqueueResponse("/rates_api_response.json")
        val response = this.service.getLatestRates("EUR").execute()
        assert(response.isSuccessful)
        MatcherAssert.assertThat(response.body()!!.baseCurrency, CoreMatchers.`is`("EUR"))
        MatcherAssert.assertThat(response.body()!!.rates["AUD"], CoreMatchers.`is`(1.603))
    }
}
