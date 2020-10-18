package com.hackheroes.healthybody.api.network_responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BmiResponse(
    @SerializedName("weight")
    @Expose
    val weight: Weight,
    @SerializedName("height")
    @Expose
    val height: Height,
    @SerializedName("bmi")
    @Expose
    val bmi: Bmi,
    @SerializedName("ideal_weight")
    @Expose
    val ideal_weight: String
) {

    data class Weight (
        @SerializedName("kg")
        @Expose
        val kg : Double
    )

    data class Height (
        @SerializedName("cm")
        @Expose
        val cm : Double
    )

    data class Bmi (
        @SerializedName("value")
        @Expose
        val value : Double,
        @SerializedName("status")
        @Expose
        val status : String,
        @SerializedName("risk")
        @Expose
        val risk : String
    )
}
