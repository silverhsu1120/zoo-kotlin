package com.silver.zookotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silver.zookotlin.model.House

class HouseViewModel : ViewModel() {

    private val house: MutableLiveData<House> = MutableLiveData()

    fun getHouse(): MutableLiveData<House> = this.house
}