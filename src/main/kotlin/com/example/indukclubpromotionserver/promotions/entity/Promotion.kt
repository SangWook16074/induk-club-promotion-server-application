package com.example.indukclubpromotionserver.promotions.entity

import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

//*
// Promotion Entity 정의
// */
@Entity
class Promotion (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long?,

    @Column(nullable = false, length = 100,)
    var title : String,

    @Column(nullable = false, length = 100,)
    var club : String,

    @Column(nullable = false, length = 1000,)
    var content : String,

    @Column(nullable = false, length = 10)
    var userName : String,

    @Column(nullable = false, updatable = false)
    var createAt : LocalDateTime,

    @Column(nullable = false, updatable = false)
    var closeAt : LocalDateTime,

    @Column(nullable = false)
    var begin : LocalDateTime,

    @Column(nullable = false)
    var end : LocalDateTime,

    @Column(nullable = false)
    var requiredPeople : Int,

    @Column(nullable = false, length = 1000)
    var contentOfActivity : String,
) {
    fun toResponse() : PromotionResponseDto = PromotionResponseDto(
        userName = userName,
        club = club,
        title = title,
        content = content,
        createAt = createAt,
        closeAt = closeAt,
        begin = begin,
        end = end,
        contentOfActivity = contentOfActivity,
        requiredPeople = requiredPeople,
    )
}