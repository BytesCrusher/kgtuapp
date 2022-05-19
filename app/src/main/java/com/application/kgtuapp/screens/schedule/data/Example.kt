package com.application.kgtuapp.screens.schedule.data


import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated
//import javax.annotation.processing.Generated


//@Generated("jsonschema2pojo")
class Example : Parcelable {
    @SerializedName("remoteStudyGroup")
    @Expose
    private var remoteStudyGroup: RemoteStudyGroup? = null

    @SerializedName("remoteTimetable")
    @Expose
    private var remoteTimetable: List<RemoteTimetable?>? = null

    protected constructor(`in`: Parcel) {
        remoteStudyGroup = `in`.readValue(RemoteStudyGroup::class.java.getClassLoader()) as RemoteStudyGroup?
        `in`.readList(
            remoteTimetable!!,
            com.application.kgtuapp.screens.schedule.data.RemoteTimetable::class.java.getClassLoader()
        )
    }

    constructor() {}

    fun getStudyGroup(): RemoteStudyGroup? {
        return remoteStudyGroup
    }

    fun setStudyGroup(remoteStudyGroup: RemoteStudyGroup?) {
        this.remoteStudyGroup = remoteStudyGroup
    }

    fun getTimetable(): List<RemoteTimetable?>? {
        return remoteTimetable
    }

    fun setTimetable(remoteTimetable: List<RemoteTimetable?>?) {
        this.remoteTimetable = remoteTimetable
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Example::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("remoteStudyGroup")
        sb.append('=')
        sb.append(if (remoteStudyGroup == null) "<null>" else remoteStudyGroup)
        sb.append(',')
        sb.append("remoteTimetable")
        sb.append('=')
        sb.append(if (remoteTimetable == null) "<null>" else remoteTimetable)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(remoteStudyGroup)
        dest.writeList(remoteTimetable)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<Example?> = object : Creator<Example?> {
            override fun createFromParcel(`in`: Parcel): Example? {
                return Example(`in`)
            }

            override fun newArray(size: Int): Array<Example?> {
                return arrayOfNulls(size)
            }
        }
    }
}