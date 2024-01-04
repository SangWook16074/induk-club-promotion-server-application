package com.example.indukclubpromotionserver.member.controller

import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.dto.BaseResponse
import com.example.indukclubpromotionserver.member.dto.LoginDto
import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto) : BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }

}