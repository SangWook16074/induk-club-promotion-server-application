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
        if (checkAlreadySignUp(memberDto.email)) {
            throw InvalidInputException("email", "이미 등록된 이메일입니다.")
        }
        // 회원정보 저장
//        memberRepository.save(memberDto.toEntity())

        // 회원권한 저장
        val memberRole : MemberRole = MemberRole(null, ROLE.MEMBER, memberDto.toEntity())
        memberRoleRepository.save(memberRole)
        return "회원가입이 완료되었습니다."
    }

    /**
     * 중복회원인지
     * 확인하는 함수
     */
    fun checkAlreadySignUp(email : String) : Boolean {
        val member : Member? = memberRepository.findByEmail(email)
        // 회원이 존재하면 true
        // 존재하지 않으면 false
        print(member)
        return member != null
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
