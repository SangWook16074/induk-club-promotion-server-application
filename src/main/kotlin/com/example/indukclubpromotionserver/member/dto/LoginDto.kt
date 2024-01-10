package com.example.indukclubpromotionserver.member.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginDto(
    @JsonProperty("email")
    private val _email: String?,
    @JsonProperty("password")
    private val _password: String?,
) {
    val email : String
        get() = _email!!

    val password : String
        get() = _password!!
}