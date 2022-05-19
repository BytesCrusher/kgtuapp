package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated

//@Generated("jsonschema2pojo")
class RemoteTeacher : Parcelable {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("shortName")
    @Expose
    var shortName: String? = null

    @SerializedName("fullName")
    @Expose
    var fullName: String? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        shortName = `in`.readValue(String::class.java.classLoader) as String?
        fullName = `in`.readValue(String::class.java.classLoader) as String?
    }

    constructor() {}

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteTeacher::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("id")
        sb.append('=')
        sb.append(id)
        sb.append(',')
        sb.append("shortName")
        sb.append('=')
        sb.append(if (shortName == null) "<null>" else shortName)
        sb.append(',')
        sb.append("fullName")
        sb.append('=')
        sb.append(if (fullName == null) "<null>" else fullName)
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
        dest.writeValue(shortName)
        dest.writeValue(fullName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteTeacher?> = object : Creator<RemoteTeacher?> {
            override fun createFromParcel(`in`: Parcel): RemoteTeacher? {
                return RemoteTeacher(`in`)
            }

            override fun newArray(size: Int): Array<RemoteTeacher?> {
                return arrayOfNulls(size)
            }
        }
    }
}