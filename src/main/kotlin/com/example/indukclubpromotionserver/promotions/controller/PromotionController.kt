package com.example.indukclubpromotionserver.promotions.controller

import com.example.indukclubpromotionserver.promotions.dto.PromotionRequestDto
import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import com.example.indukclubpromotionserver.promotions.entity.Promotion
import com.example.indukclubpromotionserver.promotions.service.PromotionSerivce
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/promotions")
class PromotionController {

    @Autowired
    private lateinit var service : PromotionSerivce

    @GetMapping
    private fun getPromotions() : ResponseEntity<List<PromotionResponseDto>> {
        return service.getPromotions()
    }

    @PostMapping
    private fun postPromotion(@RequestBody promotionRequestDto: PromotionRequestDto) : ResponseEntity<PromotionResponseDto> {
        return service.postPromotion(promotionRequestDto)
    }
}