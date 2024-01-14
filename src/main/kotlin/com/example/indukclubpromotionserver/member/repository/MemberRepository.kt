package com.example.indukclubpromotionserver.member.repository

import com.example.indukclubpromotionserver.member.entity.*
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long?> {
    fun findByEmail(email : String) : Member?
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long?>

interface KaKaoRepository : JpaRepository<KaKao, String?>

interface GoogleRepository : JpaRepository<Google, String?>

interface AppleRepository : JpaRepository<Apple, String?>