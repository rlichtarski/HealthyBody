package com.hackheroes.healthybody.api

import androidx.lifecycle.LiveData
import com.hackheroes.healthybody.api.network_responses.BmiResponse
import com.hackheroes.healthybody.api.request_models.BmiNetworkRequest
import com.hackheroes.healthybody.util.GenericApiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BmiApi {

    @POST(".")
    fun postBmiValue(@Body bmiNetworkRequest: BmiNetworkRequest): LiveData<GenericApiResponse<BmiResponse>>

}