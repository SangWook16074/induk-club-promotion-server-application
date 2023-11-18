package com.example.indukclubpromotionserver.promotions.dto

import com.example.indukclubpromotionserver.promotions.entity.Promotion
import java.time.LocalDate

class PromotionResponseDto (
    var id : Long?,
    var title : String,
    var content : String,
    var userName : String,
    var createAt : LocalDate,
    var closeAt : LocalDate,
    var begin : LocalDate,
    var end : LocalDate,
    var requiredPeople : Int,
    var contentOfActivity : String,

) {
    fun toResponse() = PromotionResponseDto(
        id = id,
        title = title,
        content = content,
        userName = userName,
        createAt = createAt,
        closeAt = closeAt,
        begin = begin,
        end = end,
        requiredPeople = requiredPeople,
        contentOfActivity = contentOfActivity,
    )
}