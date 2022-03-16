package com.application.kgtuapp.Classes

import java.util.Date

class Note (
    private val Id: Int,
    private val userId: Int,
    private val date: Date,
    private val deadlineDate: Date,
    private val done: Boolean,
    private val text: String,
    private val repeat: Boolean,
    private val dateRepeat: Date,

){
    fun getClassId():Int?{
        return Id
    }
    fun getClassUserId():Int?{
        return userId
    }
    override fun toString(): String {
        return "$Id, $userId, $date, $deadlineDate, $done, $text, $repeat, $dateRepeat "

    }
}