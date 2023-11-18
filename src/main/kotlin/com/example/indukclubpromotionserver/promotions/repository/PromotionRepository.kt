package com.example.indukclubpromotionserver.promotions.repository

import com.example.indukclubpromotionserver.promotions.dto.PromotionRequestDto
import com.example.indukclubpromotionserver.promotions.entity.Promotion
import org.springframework.data.repository.CrudRepository

//*
// 프로모션을 가지고오기 위한 Repository Interface
// */
interface PromotionRepository : CrudRepository<Promotion, Long?>