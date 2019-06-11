package com.silver.zookotlin.model.api

import com.google.gson.annotations.SerializedName
import com.silver.zookotlin.model.bean.Plant

data class ApiPlantRes(
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("sort")
    var sort: String,
    @SerializedName("results")
    var results: MutableList<Plant>
)