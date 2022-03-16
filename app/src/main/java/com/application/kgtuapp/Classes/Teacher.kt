package com.application.kgtuapp.Classes

class Teacher (
    private val teacherId: Int,
    private val teacherFstName: String,
    private val teacherLstName: String,
    private val teacherMidName: String,
    private val Audience: String,
    private val phoneNumber: String,
    private val email: String,
){
    fun getClassNumberId():Int?{
        return teacherId
    }
    override fun toString(): String {
        return "$teacherId, $teacherFstName, $teacherLstName, $teacherMidName, $Audience, $phoneNumber, $email"

    }
}