package com.example.indukclubpromotionserver.member.controller

import com.example.indukclubpromotionserver.member.dto.MemberDto
import com.example.indukclubpromotionserver.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController (
    private val memberService: MemberService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody memberDto: MemberDto) : String {
        return memberService.signUp(memberDto)
    }
}