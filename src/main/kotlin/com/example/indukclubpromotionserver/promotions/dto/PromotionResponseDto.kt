package com.example.indukclubpromotionserver.promotions.dto

import com.example.indukclubpromotionserver.promotions.entity.Promotion
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class PromotionResponseDto (
    var title : String,
    var content : String,
    var userName : String,
    var club : String,
    var createAt : LocalDateTime,
    var closeAt : LocalDateTime,
    var begin : LocalDateTime,
    var end : LocalDateTime,
    var requiredPeople : Int,
    var contentOfActivity : String,
)