package com.example.indukclubpromotionserver.promotions.controller

import com.example.indukclubpromotionserver.common.dto.BaseResponse
import com.example.indukclubpromotionserver.common.dto.CustomUser
import com.example.indukclubpromotionserver.promotions.dto.PromotionRequestDto
import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import com.example.indukclubpromotionserver.promotions.service.PromotionSerivce
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/promotions")
class PromotionController {

    @Autowired
    private lateinit var service : PromotionSerivce

    @GetMapping
    private fun getPromotions() : BaseResponse<List<PromotionResponseDto>> {
        val result = service.getPromotions()
        return BaseResponse(data = result)
    }

    @PostMapping("/post")
    private fun postPromotion(@RequestBody promotionRequestDto: PromotionRequestDto) : BaseResponse<PromotionResponseDto> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).id
        val result = service.postPromotion(promotionRequestDto, userId)
        return BaseResponse(data = result)
    }
}