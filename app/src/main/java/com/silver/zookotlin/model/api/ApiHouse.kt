package com.silver.zookotlin.model.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHouse {

    @GET("apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun read(
        @Query("q") query: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Observable<ResponseBody>
}