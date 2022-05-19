package com.application.kgtuapp.screens.schedule.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.application.kgtuapp.Classes.Teacher
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
//import javax.annotation.Generated

//@Generated("jsonschema2pojo")
class RemoteLesson : Parcelable {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("group")
    @Expose
    private var group: RemoteGroup? = null

    @SerializedName("subGroup")
    @Expose
    private var subGroup: RemoteSubGroup__1? = null

    @SerializedName("discipline")
    @Expose
    private var discipline: RemoteDiscipline? = null

    @SerializedName("remoteLessonType")
    @Expose
    private var remoteLessonType: RemoteLessonType? = null

    @SerializedName("remoteTimeSlot")
    @Expose
    private var remoteTimeSlot: RemoteTimeSlot? = null

    @SerializedName("classroom")
    @Expose
    private var classroom: RemoteClassroom? = null

    @SerializedName("teacher")
    @Expose
    var teacher: Teacher? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readValue(Int::class.javaPrimitiveType!!.classLoader) as Int
        date = `in`.readValue(String::class.java.classLoader) as String?
        group = `in`.readValue(RemoteGroup::class.java.getClassLoader()) as RemoteGroup?
        subGroup = `in`.readValue(RemoteSubGroup__1::class.java.getClassLoader()) as RemoteSubGroup__1?
        discipline = `in`.readValue(RemoteDiscipline::class.java.getClassLoader()) as RemoteDiscipline?
        remoteLessonType = `in`.readValue(RemoteLessonType::class.java.getClassLoader()) as RemoteLessonType?
        remoteTimeSlot = `in`.readValue(RemoteTimeSlot::class.java.getClassLoader()) as RemoteTimeSlot?
        classroom = `in`.readValue(RemoteClassroom::class.java.getClassLoader()) as RemoteClassroom?
        teacher = `in`.readValue(Teacher::class.java.classLoader) as Teacher?
    }

    constructor() {}

    fun getGroup(): RemoteGroup? {
        return group
    }

    fun setGroup(group: RemoteGroup?) {
        this.group = group
    }

    fun getSubGroup(): RemoteSubGroup__1? {
        return subGroup
    }

    fun setSubGroup(subGroup: RemoteSubGroup__1?) {
        this.subGroup = subGroup
    }

    fun getDiscipline(): RemoteDiscipline? {
        return discipline
    }

    fun setDiscipline(discipline: RemoteDiscipline?) {
        this.discipline = discipline
    }

    fun getLessonType(): RemoteLessonType? {
        return remoteLessonType
    }

    fun setLessonType(remoteLessonType: RemoteLessonType?) {
        this.remoteLessonType = remoteLessonType
    }

    fun getTimeSlot(): RemoteTimeSlot? {
        return remoteTimeSlot
    }

    fun setTimeSlot(remoteTimeSlot: RemoteTimeSlot?) {
        this.remoteTimeSlot = remoteTimeSlot
    }

    fun getClassroom(): RemoteClassroom? {
        return classroom
    }

    fun setClassroom(classroom: RemoteClassroom?) {
        this.classroom = classroom
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(RemoteLesson::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("id")
        sb.append('=')
        sb.append(id)
        sb.append(',')
        sb.append("date")
        sb.append('=')
        sb.append(if (date == null) "<null>" else date)
        sb.append(',')
        sb.append("group")
        sb.append('=')
        sb.append(if (group == null) "<null>" else group)
        sb.append(',')
        sb.append("subGroup")
        sb.append('=')
        sb.append(if (subGroup == null) "<null>" else subGroup)
        sb.append(',')
        sb.append("discipline")
        sb.append('=')
        sb.append(if (discipline == null) "<null>" else discipline)
        sb.append(',')
        sb.append("remoteLessonType")
        sb.append('=')
        sb.append(if (remoteLessonType == null) "<null>" else remoteLessonType)
        sb.append(',')
        sb.append("remoteTimeSlot")
        sb.append('=')
        sb.append(if (remoteTimeSlot == null) "<null>" else remoteTimeSlot)
        sb.append(',')
        sb.append("classroom")
        sb.append('=')
        sb.append(if (classroom == null) "<null>" else classroom)
        sb.append(',')
        sb.append("teacher")
        sb.append('=')
        sb.append(if (teacher == null) "<null>" else teacher)
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
        dest.writeValue(date)
        dest.writeValue(group)
        dest.writeValue(subGroup)
        dest.writeValue(discipline)
        dest.writeValue(remoteLessonType)
        dest.writeValue(remoteTimeSlot)
        dest.writeValue(classroom)
        dest.writeValue(teacher)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Creator<RemoteLesson?> = object : Creator<RemoteLesson?> {
            override fun createFromParcel(`in`: Parcel): RemoteLesson? {
                return RemoteLesson(`in`)
            }

            override fun newArray(size: Int): Array<RemoteLesson?> {
                return arrayOfNulls(size)
            }
        }
    }
}