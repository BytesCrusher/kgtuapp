package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated

//@Generated("jsonschema2pojo")
class RemoteSubGroup__1 : Parcelable {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("studyGroup")
    @Expose
    var studyGroup = 0

    @SerializedName("numberInGroup")
    @Expose
    var numberInGroup = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        studyGroup = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        numberInGroup = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        name = `in`.readValue(String::class.java.classLoader) as String?
    }

    constructor() {}

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteSubGroup__1::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("id")
        sb.append('=')
        sb.append(id)
        sb.append(',')
        sb.append("studyGroup")
        sb.append('=')
        sb.append(studyGroup)
        sb.append(',')
        sb.append("numberInGroup")
        sb.append('=')
        sb.append(numberInGroup)
        sb.append(',')
        sb.append("name")
        sb.append('=')
        sb.append(if (name == null) "<null>" else name)
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
        dest.writeValue(studyGroup)
        dest.writeValue(numberInGroup)
        dest.writeValue(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteSubGroup__1?> = object : Creator<RemoteSubGroup__1?> {
            override fun createFromParcel(`in`: Parcel): RemoteSubGroup__1? {
                return RemoteSubGroup__1(`in`)
            }

            override fun newArray(size: Int): Array<RemoteSubGroup__1?> {
                return arrayOfNulls(size)
            }
        }
    }
}