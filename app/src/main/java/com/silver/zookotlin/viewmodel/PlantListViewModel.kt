package com.silver.zookotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.silver.zookotlin.model.bean.Plant
import com.silver.zookotlin.model.api.ApiPlantRes
import com.silver.zookotlin.model.api.ApiServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlantListViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    private val plants = MutableLiveData<MutableList<Plant>>()

    fun getPlants(): MutableLiveData<MutableList<Plant>> = this.plants

    fun fetchPlantList(query: String?, limit: Int?, offset: Int?) {
        disposable.add(ApiServiceManager.getInstance().getApiPlant()
            .read(query, limit, offset)
            .subscribeOn(Schedulers.io())
            .map { t ->
                val json = t.string()
                val obj = Gson().fromJson(json, JsonObject::class.java)
                val result = obj.getAsJsonObject("result").toString()
                Gson().fromJson(result, ApiPlantRes::class.java)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t -> plants.value = t?.results }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}