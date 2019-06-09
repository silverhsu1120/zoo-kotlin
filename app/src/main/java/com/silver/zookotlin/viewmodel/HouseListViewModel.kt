package com.silver.zookotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silver.zookotlin.model.House

class HouseListViewModel : ViewModel() {

    private val houses = MutableLiveData<MutableList<House>>()

    fun getHouses(): MutableLiveData<MutableList<House>> = this.houses
}