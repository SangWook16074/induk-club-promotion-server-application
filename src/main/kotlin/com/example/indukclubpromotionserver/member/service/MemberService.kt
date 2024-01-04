package com.example.indukclubpromotionserver.member.service

import com.example.indukclubpromotionserver.common.authority.JwtTokenProvider
import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.exception.InvalidInputException
import com.example.indukclubpromotionserver.common.status.ROLE
import com.example.indukclubpromotionserver.member.dto.LoginDto
import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.entity.Member
import com.example.indukclubpromotionserver.member.entity.MemberRole
import com.example.indukclubpromotionserver.member.repository.MemberRepository
import com.example.indukclubpromotionserver.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService (
    private val memberRepository : MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder : AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    /**
     * 회원가입
     */
    fun signUp(memberDto: MemberDto) : String {
        var member : Member? = memberRepository.findByEmail(memberDto.email)
        if (member != null) {
            throw InvalidInputException("email", "이미 등록된 이메일입니다.")
        }
        val memberRole : MemberRole = MemberRole(null, ROLE.MEMBER, memberDto.toEntity())

        memberRepository.save(memberDto.toEntity())
        memberRoleRepository.save(memberRole)
        return "회원가입이 완료되었습니다."

    }

    /**
     * 로그인 후 토큰 발행
     */
    fun login(loginDto: LoginDto) : TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}
