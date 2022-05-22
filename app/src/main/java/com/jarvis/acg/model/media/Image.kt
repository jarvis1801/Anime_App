package com.jarvis.acg.model.media

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity(tableName = "image")
class Image(
    var imageWidth: Int? = null,
    var imageHeight: Int? = null,

    var imageString: String? = null
) : Media(), Parcelable {
    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        created_at = parcel.readString()
        updated_at = parcel.readString()
        url = parcel.readString()
        order = parcel.readValue(Int::class.java.classLoader) as? Int
        fileSize = parcel.readValue(Int::class.java.classLoader) as? Int
        imageWidth = parcel.readValue(Int::class.java.classLoader) as? Int
        imageHeight = parcel.readValue(Int::class.java.classLoader) as? Int
        imageString = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeString(url)
        parcel.writeValue(order)
        parcel.writeValue(fileSize)
        parcel.writeValue(imageWidth)
        parcel.writeValue(imageHeight)
        parcel.writeString(imageString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}