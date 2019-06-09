package com.silver.zookotlin.model

import android.os.Parcel
import android.os.Parcelable

data class Plant(
    var name: String,
    var alias: String,
    var intro: String,
    var type: String,
    var use: String,
    var resId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(alias)
        parcel.writeString(intro)
        parcel.writeString(type)
        parcel.writeString(use)
        parcel.writeInt(resId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plant> {
        override fun createFromParcel(parcel: Parcel): Plant {
            return Plant(parcel)
        }

        override fun newArray(size: Int): Array<Plant?> {
            return arrayOfNulls(size)
        }
    }
}
