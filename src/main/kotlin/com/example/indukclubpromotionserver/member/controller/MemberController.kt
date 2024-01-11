package com.example.indukclubpromotionserver.member.controller

import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.dto.BaseResponse
import com.example.indukclubpromotionserver.member.dto.LoginDto
import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.dto.MemberResponseDto
import com.example.indukclubpromotionserver.member.service.MemberService
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
    fun checkAlreadySignUp(@RequestBody email : String) : BaseResponse<Boolean> {
        val result = memberService.checkAlreadySignUp(email)
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
    @GetMapping("/info/{id}")
    fun searchMyInfo(@PathVariable id : Long) : BaseResponse<MemberResponseDto> {
        val response = memberService.searchMyInfo(id)
        return BaseResponse(data = response)
    }

}