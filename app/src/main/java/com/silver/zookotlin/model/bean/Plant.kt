package com.silver.zookotlin.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Plant(
    @SerializedName("F_Name_Ch")
    val chineseName: String,
    @SerializedName("F_Name_En")
    var englishName: String,
    @SerializedName("F_AlsoKnown")
    var alias: String,
    @SerializedName("F_Brief")
    var brief: String,
    @SerializedName("F_Feature")
    var feature: String,
    @SerializedName("F_Function&Application")
    var function: String,
    @SerializedName("F_Pic01_URL")
    var picUrl: String,
    @SerializedName("F_Update")
    var update: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(chineseName)
        parcel.writeString(englishName)
        parcel.writeString(alias)
        parcel.writeString(brief)
        parcel.writeString(feature)
        parcel.writeString(function)
        parcel.writeString(picUrl)
        parcel.writeString(update)
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
