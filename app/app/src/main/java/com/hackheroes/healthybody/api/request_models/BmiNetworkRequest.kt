package com.hackheroes.healthybody.api.request_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BmiNetworkRequest(
    @SerializedName("weight")
    @Expose
    val weight: Weight,
    @SerializedName("height")
    @Expose
    val height: Height,
    @SerializedName("sex")
    @Expose
    val sex: String,
    @SerializedName("age")
    @Expose
    val age: String
)

data class Height(
    @SerializedName("value")
    @Expose
    val value: String,
    @SerializedName("unit")
    @Expose
    val unit: String
)

data class Weight(
    @SerializedName("value")
    @Expose
    val value: String,
    @SerializedName("unit")
    @Expose
    val unit: String
)