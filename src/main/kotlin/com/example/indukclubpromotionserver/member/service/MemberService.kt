package com.example.indukclubpromotionserver.member.service

import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.dto.toEntity
import com.example.indukclubpromotionserver.member.entity.Member
import com.example.indukclubpromotionserver.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService (
    private val memberRepository : MemberRepository
) {
    fun signUp(memberDto: MemberDto) : String {
        var member : Member? = memberRepository.findbyEmail(memberDto.email)
        if (member != null) {
            return "이미 등록된 회원입니다."
        } else {
            memberRepository.save(memberDto.toEntity())
            return "회원가입이 완료되었습니다."
        }
    }
}
