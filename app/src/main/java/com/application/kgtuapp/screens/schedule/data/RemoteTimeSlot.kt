package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated

//@Generated("jsonschema2pojo")
class RemoteTimeSlot : Parcelable {
    @SerializedName("slot")
    @Expose
    var slot = 0

    @SerializedName("startTime")
    @Expose
    var startTime: String? = null

    @SerializedName("endTime")
    @Expose
    var endTime: String? = null

    protected constructor(`in`: Parcel) {
        slot = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        startTime = `in`.readValue(String::class.java.classLoader) as String?
        endTime = `in`.readValue(String::class.java.classLoader) as String?
    }

    constructor() {}

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteTimeSlot::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("slot")
        sb.append('=')
        sb.append(slot)
        sb.append(',')
        sb.append("startTime")
        sb.append('=')
        sb.append(if (startTime == null) "<null>" else startTime)
        sb.append(',')
        sb.append("endTime")
        sb.append('=')
        sb.append(if (endTime == null) "<null>" else endTime)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(slot)
        dest.writeValue(startTime)
        dest.writeValue(endTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteTimeSlot?> = object : Creator<RemoteTimeSlot?> {
            override fun createFromParcel(`in`: Parcel): RemoteTimeSlot? {
                return RemoteTimeSlot(`in`)
            }

            override fun newArray(size: Int): Array<RemoteTimeSlot?> {
                return arrayOfNulls(size)
            }
        }
    }
}