package com.silver.zookotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.silver.zookotlin.util.readMutableList

data class House(
    var name: String,
    var intro: String,
    var info: String,
    var resId: Int,
    var plants: MutableList<Plant>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readMutableList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(intro)
        parcel.writeString(info)
        parcel.writeInt(resId)
        parcel.writeList(plants)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<House> {
        override fun createFromParcel(parcel: Parcel): House {
            return House(parcel)
        }

        override fun newArray(size: Int): Array<House?> {
            return arrayOfNulls(size)
        }
    }
}