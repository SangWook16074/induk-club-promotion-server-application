package com.example.indukclubpromotionserver.promotions.service

import com.example.indukclubpromotionserver.promotions.dto.PromotionRequestDto
import com.example.indukclubpromotionserver.promotions.dto.PromotionResponseDto
import com.example.indukclubpromotionserver.promotions.entity.Promotion
import com.example.indukclubpromotionserver.promotions.repository.PromotionRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDate

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
                    userName = "한상욱",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDate.now(),
                    closeAt = LocalDate.now(),
                    begin = LocalDate.now(),
                    end = LocalDate.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
                Promotion(
                    id = 2,
                    userName = "한상욱",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDate.now(),
                    closeAt = LocalDate.now(),
                    begin = LocalDate.now(),
                    end = LocalDate.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
                Promotion(
                    id = 3,
                    userName = "한상욱",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDate.now(),
                    closeAt = LocalDate.now(),
                    begin = LocalDate.now(),
                    end = LocalDate.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
                Promotion(
                    id = 4,
                    userName = "한상욱",
                    title = "A&I 신규 인원 모집",
                    content = "2022학년도에 진행한 스터디그룹 활동으로 처음 모여 모바일 앱 개발 공부 및 협업 프로젝트를 진행했으며, 플레이스토어와 앱스토어에 정식 출시까비 했습니다. 그리고 현재는 새로운 프로젝트를 준비하고 있습니다.",
                    createAt = LocalDate.now(),
                    closeAt = LocalDate.now(),
                    begin = LocalDate.now(),
                    end = LocalDate.now(),
                    contentOfActivity = "UI/UX 디자인  프로젝트 화면 (UI)을 디자인하고, 이를 위한 미디어 파일 제작합니다.",
                    requiredPeople = 10,
                ),
            )

        )
    }

    @GetMapping
    fun getPromotions() : ResponseEntity<List<PromotionResponseDto>> {
        val promotions = repository.findAll()
        return ResponseEntity.ok(promotions.map { it.toResponse() })
    }

}