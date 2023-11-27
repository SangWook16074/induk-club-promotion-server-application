package com.example.indukclubpromotionserver.promotions.dto

import com.example.indukclubpromotionserver.promotions.entity.Promotion
import java.time.LocalDate
import java.util.*

data class PromotionRequestDto (
    var id : UUID,
    var title : String,
    var content : String,
    var userName : String,
    var club : String,
    var createAt : LocalDate,
    var closeAt : LocalDate,
    var begin : LocalDate,
    var end : LocalDate,
    var requiredPeople : Int,
    var contentOfActivity : String,
) {
    fun toEntity() : Promotion = Promotion(
        id = id,
        title = title,
        content = content,
        userName = userName,
        club = club,
        createAt = createAt,
        closeAt = closeAt,
        begin = begin,
        end = end,
        requiredPeople = requiredPeople,
        contentOfActivity = contentOfActivity,
    )
}

