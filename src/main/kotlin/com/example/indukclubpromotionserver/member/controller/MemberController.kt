package com.example.indukclubpromotionserver.member.controller

import com.example.indukclubpromotionserver.common.authority.TokenInfo
import com.example.indukclubpromotionserver.common.dto.BaseResponse
import com.example.indukclubpromotionserver.member.dto.LoginDto
import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.dto.MemberResponseDto
import com.example.indukclubpromotionserver.member.dto.SocialMemberDto
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

    @PostMapping("/social/kakao/duplicate/{id}")
    fun checkSignUpWithKakao(@PathVariable id : String) : BaseResponse<Boolean> {
        val response = memberService.checkSignUpWithKaKao(id)
        return BaseResponse(data = response)
    }

    @PostMapping("/social/google/duplicate/{id}")
    fun checkSignUpWithGoogle(@PathVariable id : String) : BaseResponse<Boolean> {
        val response = memberService.checkSignUpWithGoogle(id)
        return BaseResponse(data = response)
    }

    @PostMapping("/social/apple/duplicate/{id}")
    fun checkSignUpWithApple(@PathVariable id : String) : BaseResponse<Boolean> {
        val response = memberService.checkSignUpWithApple(id)
        return BaseResponse(data = response)
    }

    /**
     * 카카오로 통합회원 가입
     */
    @PostMapping("/social/kakao/signup")
    fun signUpWithKaKao(@RequestBody socialMemberDto: SocialMemberDto) : BaseResponse<String> {
        val resultMsg = memberService.signUpWithKaKao(socialMemberDto)
        return BaseResponse(data = resultMsg)
    }

    /**
     * 구글로 통합회원 가입
     */
    @PostMapping("/social/google/signup")
    fun signUpWithGoogle(@RequestBody socialMemberDto: SocialMemberDto) : BaseResponse<String> {
        val resultMsg = memberService.signUpWithGoogle(socialMemberDto)
        return BaseResponse(data = resultMsg)
    }

    /**
     * 애플로 통합회원 가입
     */
    @PostMapping("/social/apple/signup")
    fun signUpWithApple(@RequestBody socialMemberDto: SocialMemberDto) : BaseResponse<String> {
        val resultMsg = memberService.signUpWithApple(socialMemberDto)
        return BaseResponse(data = resultMsg)
    }



}