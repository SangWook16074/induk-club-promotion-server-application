package com.example.indukclubpromotionserver.member.repository

import com.example.indukclubpromotionserver.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long?> {
    fun findByEmail(email : String) : Member?
}