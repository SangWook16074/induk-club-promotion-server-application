package com.example.indukclubpromotionserver.member.dto

import com.example.indukclubpromotionserver.member.entity.Member
import jakarta.persistence.Id

data class MemberDto(
    var id: Long?,
    var email: String,
    var name:String,
    var password:String,
)

fun MemberDto.toEntity() = Member(
        id = id,
        email = email,
        name = name,
        password = password,
    )
