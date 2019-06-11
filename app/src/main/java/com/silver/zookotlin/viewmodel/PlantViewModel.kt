package com.silver.zookotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silver.zookotlin.model.bean.Plant

class PlantViewModel : ViewModel() {

    private val plant: MutableLiveData<Plant> = MutableLiveData()

    fun getPlant(): MutableLiveData<Plant> = this.plant
}