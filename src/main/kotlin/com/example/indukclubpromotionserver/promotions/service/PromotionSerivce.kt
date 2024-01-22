package com.example.indukclubpromotionserver.promotions.service

import com.example.indukclubpromotionserver.promotions.dto.PromotionRequestDto
import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import com.example.indukclubpromotionserver.promotions.entity.Promotion
import com.example.indukclubpromotionserver.promotions.repository.PromotionRepository
import jakarta.annotation.PostConstruct
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

    @PostConstruct
    fun loadData() {
        repository.saveAll(
            listOf(
                Promotion(
                    id = 1,
                    userId = 1,
                    club = "A&I",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDateTime.now(),
                    closeAt = LocalDateTime.now(),
                    begin = LocalDateTime.now(),
                    end = LocalDateTime.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
                Promotion(
                    id = 2,
                    userId = 1,


                    club = "A&I",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDateTime.now(),
                    closeAt = LocalDateTime.now(),
                    begin = LocalDateTime.now(),
                    end = LocalDateTime.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
                Promotion(
                    id = 3,
                    userId = 1,


                    club = "A&I",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDateTime.now(),
                    closeAt = LocalDateTime.now(),
                    begin = LocalDateTime.now(),
                    end = LocalDateTime.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
                Promotion(
                    id = 4,
                    userId = 1,

                    club = "A&I",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDateTime.now(),
                    closeAt = LocalDateTime.now(),
                    begin = LocalDateTime.now(),
                    end = LocalDateTime.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
            )

        )
    }

    fun getPromotions() : List<PromotionResponseDto> {
        val promotions = repository.findAll()
        return promotions.map { it.toResponse() }
    }

    fun postPromotion(promotionRequestDto: PromotionRequestDto, userId : Long) : PromotionResponseDto {

        val promotion = Promotion(
            id = promotionRequestDto.id,
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
        )
        repository.save(promotion)
        return promotion.toResponse()
    }

}