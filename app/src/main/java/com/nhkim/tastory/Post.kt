package com.nhkim.tastory

import android.os.Parcel
import android.os.Parcelable

data class Post(
    val name: String?,
    val profile: Int,
    val thumbnail: Int, // 이미지 필드를 Int 타입으로 변경
    val title: String?,
    val content: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        profile = parcel.readInt(),
        thumbnail = parcel.readInt(), // Int 타입으로 읽기
        title = parcel.readString(),
        content = parcel.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(profile)
        parcel.writeInt(thumbnail) // Int 타입으로 쓰기
        parcel.writeString(title)
        parcel.writeString(content)
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}
