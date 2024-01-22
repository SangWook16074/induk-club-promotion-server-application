package com.example.indukclubpromotionserver.member.controller

import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.dto.BaseResponse
import com.example.indukclubpromotionserver.common.dto.CustomUser
import com.example.indukclubpromotionserver.member.dto.ClubInfoDto
import com.example.indukclubpromotionserver.member.dto.LoginDto
import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.dto.MemberResponseDto
import com.example.indukclubpromotionserver.member.service.MemberService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberController (
    private val memberService: MemberService
) {

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody memberDto: MemberDto) : BaseResponse<Unit> {
        val resultMsg : String = memberService.signUp(memberDto)
        return BaseResponse(message = resultMsg)
    }

    /**
     * 이메일 중복 확인
     */
    @PostMapping("/duplicate")
    fun checkEmail(@RequestBody email : String) : BaseResponse<Boolean> {
        val result = memberService.checkEmail(email)
        return BaseResponse(data = result)
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto) : BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }

    /**
     * 내 정보 조회
     */
    @GetMapping("/info")
    fun searchMyInfo() : BaseResponse<MemberResponseDto> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).id
        val response = memberService.searchMyInfo(userId)
        return BaseResponse(data = response)
    }

    /**
     * 내 동아리 정보 갱신
     */
    @PutMapping("/club")
    fun saveMyClubInfo(@RequestBody clubInfoDto: ClubInfoDto) : BaseResponse<String> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).id
        val response = memberService.saveMyClubInfo(clubInfoDto, userId)
        return BaseResponse(data = response)
    }



}