package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated


//@Generated("jsonschema2pojo")
class RemoteTimetable : Parcelable {
    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("day")
    @Expose
    var day = 0

    @SerializedName("remoteLessons")
    @Expose
    var remoteLessons: List<RemoteLesson?>? = null

    protected constructor(`in`: Parcel) {
        date = `in`.readValue(String::class.java.classLoader) as String?
        day = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        `in`.readList(remoteLessons!!, RemoteLesson::class.java.classLoader)
    }

    constructor() {}

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteTimetable::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("date")
        sb.append('=')
        sb.append(if (date == null) "<null>" else date)
        sb.append(',')
        sb.append("day")
        sb.append('=')
        sb.append(day)
        sb.append(',')
        sb.append("remoteLessons")
        sb.append('=')
        sb.append(if (remoteLessons == null) "<null>" else remoteLessons)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(date)
        dest.writeValue(day)
        dest.writeList(remoteLessons)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteTimetable?> = object : Creator<RemoteTimetable?> {
            override fun createFromParcel(`in`: Parcel): RemoteTimetable? {
                return RemoteTimetable(`in`)
            }

            override fun newArray(size: Int): Array<RemoteTimetable?> {
                return arrayOfNulls(size)
            }
        }
    }
}