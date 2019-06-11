package com.silver.zookotlin.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class House(
    @SerializedName("E_Name")
    var name: String,
    @SerializedName("E_Info")
    var info: String,
    @SerializedName("E_Memo")
    var memo: String,
    @SerializedName("E_Category")
    var category: String,
    @SerializedName("E_Pic_URL")
    var picUrl: String,
    @SerializedName("E_URL")
    var url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(info)
        parcel.writeString(memo)
        parcel.writeString(category)
        parcel.writeString(picUrl)
        parcel.writeString(url)
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
