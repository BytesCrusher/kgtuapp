package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated

//@Generated("jsonschema2pojo")
class RemoteLessonType : Parcelable {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("nameIn1c")
    @Expose
    var nameIn1c: String? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        name = `in`.readValue(String::class.java.classLoader) as String?
        nameIn1c = `in`.readValue(String::class.java.classLoader) as String?
    }

    constructor() {}

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteLessonType::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("id")
        sb.append('=')
        sb.append(id)
        sb.append(',')
        sb.append("name")
        sb.append('=')
        sb.append(if (name == null) "<null>" else name)
        sb.append(',')
        sb.append("nameIn1c")
        sb.append('=')
        sb.append(if (nameIn1c == null) "<null>" else nameIn1c)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(name)
        dest.writeValue(nameIn1c)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteLessonType?> = object : Creator<RemoteLessonType?> {
            override fun createFromParcel(`in`: Parcel): RemoteLessonType? {
                return RemoteLessonType(`in`)
            }

            override fun newArray(size: Int): Array<RemoteLessonType?> {
                return arrayOfNulls(size)
            }
        }
    }
}