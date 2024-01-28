package com.example.indukclubpromotionserver.promotions.dto

import java.time.LocalDateTime

data class PromotionResponseDto (
    var title : String,
    var content : String,
    var userId : Long,
    var club : String,
    var createAt : LocalDateTime,
    var closeAt : LocalDateTime,
    var begin : LocalDateTime,
    var end : LocalDateTime,
    var requiredPeople : Int,
    var contentOfActivity : String,
    var url : String?,
    var images : List<String>?,
)