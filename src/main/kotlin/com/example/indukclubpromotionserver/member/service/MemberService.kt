package com.example.indukclubpromotionserver.member.service

import com.example.indukclubpromotionserver.common.authority.JwtTokenProvider
import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.exception.InvalidInputException
import com.example.indukclubpromotionserver.common.status.ROLE
import com.example.indukclubpromotionserver.member.dto.*
import com.example.indukclubpromotionserver.member.entity.ClubInfo
import com.example.indukclubpromotionserver.member.entity.Member
import com.example.indukclubpromotionserver.member.entity.MemberRole
import com.example.indukclubpromotionserver.member.repository.ClubInfoRepository
import com.example.indukclubpromotionserver.member.repository.MemberRepository
import com.example.indukclubpromotionserver.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Transactional
@Service
class MemberService (
    private val memberRepository : MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val clubInfoRepository : ClubInfoRepository,
    private val authenticationManagerBuilder : AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val javaMailSender: JavaMailSender,
) {
    /**
     * 회원가입
     */
    fun signUp(memberDto: MemberDto) : String {
        if (checkEmail(memberDto.email)) {
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
    fun checkEmail(email : String) : Boolean {
        val member : Member? = memberRepository.findByEmail(email)
        // 회원이 존재하면 true
        // 존재하지 않으면 false
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
    fun searchMyInfo(userId: Long): MemberResponseDto {
        val member: Member = memberRepository.findByIdOrNull(userId)
            ?: throw InvalidInputException("userId", "회원번호 (${userId})가 존재하지 않는 유저입니다.")
        val clubInfo: ClubInfo? = clubInfoRepository.findByMember(member)
        return MemberResponseDto(
            id = member.id!!,
            name = member.name,
            clubInfo = clubInfo?.toResponse(),
        )
    }

    /**
     * 내 동아리 정보 갱신
     */
    fun saveMyClubInfo(clubInfoDto: ClubInfoDto, userId : Long) : String {
        val member : Optional<Member> = memberRepository.findById(userId)
        val myClubInfo : ClubInfo? = clubInfoRepository.findByMember(member.get());
        if (myClubInfo == null) {
            val clubInfo = ClubInfo(
                id = null,
                clubName = clubInfoDto.clubName,
                createAt = LocalDateTime.parse(clubInfoDto.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                classify = clubInfoDto.classify,
                member = member.get()
            )
            clubInfoRepository.save(clubInfo)
            return "내 동아리 정보가 수정되었습니다."
        } else {
            val clubInfo = ClubInfo(
                id = myClubInfo.id,
                clubName = clubInfoDto.clubName,
                createAt = LocalDateTime.parse(clubInfoDto.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                classify = clubInfoDto.classify,
                member = member.get()
            )
            clubInfoRepository.save(clubInfo)
            return "내 동아리 정보가 수정되었습니다."
        }


    }

    /**
     * 비밀번호 초기화
     * 그리고 초기화된 비밀번호를
     * 사용자에게 전송해줌.
     */
    fun resetPassword(resetPasswordRequestDto: ResetPasswordRequestDto) : String {
        val member : Member? = memberRepository.findByEmail(resetPasswordRequestDto.email)
        return if (member == null) {
            "존재하지 않는 사용자입니다."
        } else {
            var password : String = getRandomString(12)
            var sender : SimpleMailMessage = SimpleMailMessage()
            sender.setSubject("IDCP 임시 비밀번호 발송")
            sender.setText("임시 비밀번호 : $password")
            sender.setTo(resetPasswordRequestDto.email)
            javaMailSender.send(sender)
            member.resetPassword(password)
            memberRepository.save(member)
            "메일을 전송하였습니다 !"
        }
    }

    /**
     * 랜덤 비밀번호 생성 메소드
     */
    fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return List(length) { charset.random() }
            .joinToString("")
    }
}
