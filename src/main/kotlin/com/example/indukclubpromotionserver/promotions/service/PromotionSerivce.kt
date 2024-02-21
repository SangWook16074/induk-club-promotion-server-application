package com.example.indukclubpromotionserver.promotions.service

import com.example.indukclubpromotionserver.promotions.dto.PromotionRequestDto
import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import com.example.indukclubpromotionserver.promotions.entity.Promotion
import com.example.indukclubpromotionserver.promotions.repository.PromotionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//*
// Promotino Repository의 실질적인 Business Logic
// */
@Service
class PromotionSerivce {
    @Autowired
    private lateinit var repository : PromotionRepository


    /**
     * 홍보글 가져오기
     */
    fun getPromotions() : List<PromotionResponseDto> {
        val promotions = repository.findAll()
        return promotions.map { it.toResponse() }
    }

    /**
     * 홍보글 작성하기
     */
    fun postPromotion(promotionRequestDto: PromotionRequestDto, userId : Long) : PromotionResponseDto {

        val promotion = Promotion(
            null,
            title = promotionRequestDto.title,
            content = promotionRequestDto.content,
            userId = userId,
            club = promotionRequestDto.club,
            createAt = LocalDateTime.parse(promotionRequestDto.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
            closeAt = LocalDateTime.parse(promotionRequestDto.closeAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
            begin = LocalDateTime.parse(promotionRequestDto.begin, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
            end = LocalDateTime.parse(promotionRequestDto.end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
            requiredPeople = promotionRequestDto.requiredPeople,
            contentOfActivity = promotionRequestDto.contentOfActivity,
            url = promotionRequestDto.url,
            images = promotionRequestDto.images,
        )
        repository.save(promotion)
        return promotion.toResponse()
    }

}