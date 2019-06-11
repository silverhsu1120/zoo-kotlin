package com.silver.zookotlin.model.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceManager private constructor() {

    private val retrofit = buildRetrofit()

    companion object {

        private const val BASE_URL = "https://data.taipei/opendata/datalist/"

        fun getInstance(): ApiServiceManager {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = ApiServiceManager()
    }

    private fun buildRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().build()
        val gson = GsonBuilder().serializeNulls().create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getApiHouse(): ApiHouse = retrofit.create(ApiHouse::class.java)

    fun getApiPlant(): ApiPlant = retrofit.create(ApiPlant::class.java)
}

