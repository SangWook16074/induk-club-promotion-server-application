package com.example.indukclubpromotionserver.member.service

import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.dto.toEntity
import com.example.indukclubpromotionserver.member.entity.Member
import com.example.indukclubpromotionserver.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService (
    private val memberRepository : MemberRepository
) {
    fun signUp(memberDto: MemberDto) : ResponseEntity<String> {
        var member : Member? = memberRepository.findByEmail(memberDto.email)
        if (member != null) {
            return ResponseEntity("이미 등록된 회원입니다.", HttpStatus.OK)
        } else {
            memberRepository.save(memberDto.toEntity())
            return ResponseEntity("회원가입이 완료되었습니다.", HttpStatus.CREATED)
        }
    }
}
