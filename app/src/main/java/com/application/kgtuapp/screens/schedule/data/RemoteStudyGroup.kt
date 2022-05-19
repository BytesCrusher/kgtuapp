package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated

//@Generated("jsonschema2pojo")
class RemoteStudyGroup : Parcelable {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("remoteSubGroups")
    @Expose
    private var remoteSubGroups: List<RemoteSubGroup?>? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        name = `in`.readValue(String::class.java.classLoader) as String?
        `in`.readList(remoteSubGroups!!, RemoteSubGroup::class.java.getClassLoader())
    }

    constructor() {}

    fun getSubGroups(): List<RemoteSubGroup?>? {
        return remoteSubGroups
    }

    fun setSubGroups(remoteSubGroups: List<RemoteSubGroup?>?) {
        this.remoteSubGroups = remoteSubGroups
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteStudyGroup::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("id")
        sb.append('=')
        sb.append(id)
        sb.append(',')
        sb.append("name")
        sb.append('=')
        sb.append(if (name == null) "<null>" else name)
        sb.append(',')
        sb.append("remoteSubGroups")
        sb.append('=')
        sb.append(if (remoteSubGroups == null) "<null>" else remoteSubGroups)
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
        dest.writeList(remoteSubGroups)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteStudyGroup?> = object : Creator<RemoteStudyGroup?> {
            override fun createFromParcel(`in`: Parcel): RemoteStudyGroup? {
                return RemoteStudyGroup(`in`)
            }

            override fun newArray(size: Int): Array<RemoteStudyGroup?> {
                return arrayOfNulls(size)
            }
        }
    }
}