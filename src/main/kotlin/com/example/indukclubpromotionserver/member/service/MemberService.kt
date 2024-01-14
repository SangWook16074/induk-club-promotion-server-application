package com.example.indukclubpromotionserver.member.service

import com.example.indukclubpromotionserver.common.authority.JwtTokenProvider
import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.exception.InvalidInputException
import com.example.indukclubpromotionserver.common.status.ROLE
import com.example.indukclubpromotionserver.member.dto.LoginDto
import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.dto.MemberResponseDto
import com.example.indukclubpromotionserver.member.dto.SocialMemberDto
import com.example.indukclubpromotionserver.member.entity.*
import com.example.indukclubpromotionserver.member.repository.*
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService (
    private val memberRepository : MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val kaKaoRepository: KaKaoRepository,
    private val googleRepository: GoogleRepository,
    private val appleRepository: AppleRepository,
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

        // 회원정보 및 권한 저장
        val memberRole : MemberRole = MemberRole(
            null, ROLE.MEMBER, memberDto.toEntity())
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

    /**
     * 내 정보 조회
     */
    fun searchMyInfo(id : Long) : MemberResponseDto {
        val member : Member = memberRepository.findByIdOrNull(id)
            ?: throw InvalidInputException("id", "회원번호 (${id})가 존재하지 않는 유저입니다.")
        return member.toResponse()
    }

    /**
     * 카카오로 통합회원인지 확인
     */
    fun checkSignUpWithKaKao(id : String) : Boolean {
        val member : KaKao? = kaKaoRepository.findByIdOrNull(id)
        return member != null
    }

    /**
     * 구글로 통합회원인지 확인
     */
    fun checkSignUpWithGoogle(id : String) : Boolean {
        val member : Google? = googleRepository.findByIdOrNull(id)
        return member != null
    }

    /**
     * 애플로 통합회원인지 확인
     */
    fun checkSignUpWithApple(id : String) : Boolean {
        val member : Apple? = appleRepository.findByIdOrNull(id)
        return member != null
    }

    /**
     * 카카오로 통합회원 가입
     */
    fun signUpWithKaKao(socialMemberDto : SocialMemberDto) : String {
        val kaKao : KaKao = KaKao(socialMemberDto.socialId, socialMemberDto.toEntity())
        kaKaoRepository.save(kaKao)
        return "회원가입이 완료되었습니다."
    }

    /**
     * 구글로 통합회원 가입
     */
    fun signUpWithGoogle(socialMemberDto : SocialMemberDto) : String {
        val google : Google = Google(socialMemberDto.socialId, socialMemberDto.toEntity())
        googleRepository.save(google)
        return "회원가입이 완료되었습니다."
    }

    /**
     * 애플로 통합회원 가입
     */
    fun signUpWithApple(socialMemberDto : SocialMemberDto) : String {
        val apple : Apple = Apple(socialMemberDto.socialId, socialMemberDto.toEntity())
        appleRepository.save(apple)
        return "회원가입이 완료되었습니다."
    }
}
