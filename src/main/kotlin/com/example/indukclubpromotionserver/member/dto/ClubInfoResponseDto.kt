package com.example.indukclubpromotionserver.member.dto

import java.time.LocalDateTime

data class ClubInfoResponseDto(
    val id : Long?,
    val clubName : String,
    val createAt : LocalDateTime,
    val classify : String,
)
