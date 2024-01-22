package com.example.indukclubpromotionserver.promotions.entity

import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import jakarta.persistence.*
import java.time.LocalDateTime

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
    var userId : Long,

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
        userId = userId,
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