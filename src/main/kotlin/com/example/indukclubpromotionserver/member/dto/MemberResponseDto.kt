package com.example.indukclubpromotionserver.member.dto

data class MemberResponseDto(
    val id : Long,
    val name : String,
    val clubInfo: ClubInfoResponseDto?
)
