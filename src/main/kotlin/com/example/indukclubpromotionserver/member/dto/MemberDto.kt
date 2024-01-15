package com.example.indukclubpromotionserver.member.dto

import com.example.indukclubpromotionserver.member.entity.Member

data class MemberDto(
    var id: Long?,
    var email: String,
    var name:String,
    var password:String,
) {
    fun toEntity() : Member = Member(
        id = id,
        email = email,
        name = name,
        password = password,
    )
}
