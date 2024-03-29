package com.silver.zookotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.silver.zookotlin.model.bean.House
import com.silver.zookotlin.model.api.ApiHouseRes
import com.silver.zookotlin.model.api.ApiServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HouseListViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    private val houses = MutableLiveData<MutableList<House>>()

    fun getHouses(): MutableLiveData<MutableList<House>> = this.houses

    fun fetchHouseList(query: String?, limit: Int?, offset: Int?) {
        disposable.add(ApiServiceManager.getInstance().getApiHouse()
            .read(query, limit, offset)
            .subscribeOn(Schedulers.io())
            .map { t ->
                val json = t.string()
                val obj = Gson().fromJson(json, JsonObject::class.java)
                val result = obj.getAsJsonObject("result").toString()
                Gson().fromJson(result, ApiHouseRes::class.java)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t -> houses.value = t?.results }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}