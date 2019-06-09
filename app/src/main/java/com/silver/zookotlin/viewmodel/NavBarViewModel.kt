package com.silver.zookotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class NavBarViewModel : ViewModel() {

    private val state: MutableLiveData<State> = MutableLiveData()
    private val stack: MutableList<State> = ArrayList()

    fun getState(): MutableLiveData<State> = state

    fun getStack(): MutableList<State> = stack

    fun pushState(icon: Int, title: String) {
        val state = State(icon, title)
        this.stack.add(state)
        this.state.value = state
    }

    fun popState() {
        if (stack.size == 1) return
        stack.removeAt(stack.size - 1)
        state.value = stack[stack.size - 1]
    }

    inner class State(private val icon: Int, private val title: String) {

        fun getIcon(): Int = icon

        fun getTitle(): String = title
    }
}