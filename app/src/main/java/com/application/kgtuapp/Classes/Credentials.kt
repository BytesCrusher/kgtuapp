package com.application.kgtuapp.Classes

class Credentials (
    private val Id: Int,
    private val userId: Int,
    private val login: String,
    private val email: String,
    private val pass: String,
    private val name: String,
    private val role: String,
){

    override fun toString(): String {
        return "$Id, $userId, $login, $email, $pass, $name, $role"

    }
}