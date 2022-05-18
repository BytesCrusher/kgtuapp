package com.application.kgtuapp.screens.schedule

class CertainClassStartEndTime(private var id: Int, private var startClassTime: String, private var endClassTime: String) {

    fun getId():Int? {
        return this.id
    }

    fun getStartClassTime():String {
        return this.startClassTime
    }

    fun getEndClassTime():String {
        return this.endClassTime
    }
}