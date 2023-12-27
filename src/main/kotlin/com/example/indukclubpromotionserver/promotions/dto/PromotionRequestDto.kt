package com.example.indukclubpromotionserver.promotions.dto

import com.example.indukclubpromotionserver.promotions.entity.Promotion
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class PromotionRequestDto (
    var id : Long?,
    var title : String,
    var content : String,
    var userName : String,
    var club : String,
    var createAt : String,
    var closeAt : String,
    var begin : String,
    var end : String,
    var requiredPeople : Int,
    var contentOfActivity : String,
) {
    fun toEntity() : Promotion = Promotion(
        id = id,
        title = title,
        content = content,
        userName = userName,
        club = club,
        createAt = LocalDateTime.parse(createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
        closeAt = LocalDateTime.parse(closeAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
        begin = LocalDateTime.parse(begin,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
        end = LocalDateTime.parse(end,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
        requiredPeople = requiredPeople,
        contentOfActivity = contentOfActivity,
    )
}

