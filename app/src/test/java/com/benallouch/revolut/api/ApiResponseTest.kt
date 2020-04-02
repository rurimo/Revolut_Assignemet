package com.benallouch.revolut.api

import com.benallouch.revolut.api.ApiResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Exception("apiErrorResponse")
        val apiResponse = ApiResponse.error<String>(exception)
        MatcherAssert.assertThat(apiResponse.message, CoreMatchers.`is`("apiErrorResponse"))
    }

    @Test
    fun success() {
        val apiResponse = ApiResponse.of { Response.success("apiSuccessResponse") }
        if (apiResponse is ApiResponse.Success) {
            MatcherAssert.assertThat(apiResponse.data, CoreMatchers.`is`("apiSuccessResponse"))
        }
    }
}