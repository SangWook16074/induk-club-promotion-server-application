package com.example.indukclubpromotionserver.member.repository

import com.example.indukclubpromotionserver.member.entity.Member
import com.example.indukclubpromotionserver.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long?> {
    fun findByEmail(email : String) : Member?
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long?>