package com.hackheroes.healthybody.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.hackheroes.healthybody.api.BmiApi
import com.hackheroes.healthybody.api.network_responses.BmiResponse
import com.hackheroes.healthybody.api.request_models.BmiNetworkRequest
import com.hackheroes.healthybody.api.request_models.Height
import com.hackheroes.healthybody.api.request_models.Weight
import com.hackheroes.healthybody.ui.BmiViewState
import com.hackheroes.healthybody.ui.DataState
import com.hackheroes.healthybody.ui.Response
import com.hackheroes.healthybody.ui.ResponseType
import com.hackheroes.healthybody.util.ApiEmptyResponse
import com.hackheroes.healthybody.util.ApiErrorResponse
import com.hackheroes.healthybody.util.ApiSuccessResponse
import javax.inject.Inject
import javax.inject.Singleton

class BmiRepository constructor(
    private val bmiApi: BmiApi
) {

    lateinit var bmiNetworkRequest: BmiNetworkRequest

    fun bmi(): LiveData<DataState<BmiViewState>> {
        return handleRequest()
    }

    fun handleRequest(): LiveData<DataState<BmiViewState>> {
        bmiNetworkRequest = BmiNetworkRequest(
            age = "17",
            height = Height(
                unit = "cm",
                value = "158.00"
            ),
            sex = "m",
            weight = Weight(
                unit = "kg",
                value = "53.00"
            )
        )

        return bmiApi.postBmiValue(bmiNetworkRequest)
            .switchMap { response ->
                object : LiveData<DataState<BmiViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when (response) {
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = BmiViewState(
                                        response.body
                                    )
                                )
                            }
                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        "unknown", ResponseType.Dialog()
                                    )
                                )
                            }
                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        "unknown", ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }

}
