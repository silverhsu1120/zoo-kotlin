package com.silver.zookotlin.util

import android.os.Parcel

inline fun <reified T> Parcel.readMutableList(): MutableList<T> {
    @Suppress("UNCHECKED_CAST")
    return readArrayList(T::class.java.classLoader) as MutableList<T>
}
