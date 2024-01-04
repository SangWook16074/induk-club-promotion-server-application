package com.example.indukclubpromotionserver.member.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginDto(
    private val _email : String?,
    private val _password : String?,
) {
    val email : String
        get() = _email!!

    val password : String
        get() = _password!!
}