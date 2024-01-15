package com.example.indukclubpromotionserver.member.entity

import com.example.indukclubpromotionserver.common.status.ROLE
import com.example.indukclubpromotionserver.member.dto.MemberResponseDto
import jakarta.persistence.*

@Entity
@Table
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long?,

    @Column(nullable = false, length = 30)
    var email : String,

    @Column(nullable = false, length = 50, updatable = false)
    var name : String,

    @Column(length = 100)
    var password : String?,
) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = [CascadeType.ALL])
    val memberRole : List<MemberRole>? = null

    fun toResponse() : MemberResponseDto = MemberResponseDto(
        id = id!!,
        name = name,
    )
}

@Entity
class MemberRole (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long?,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    var role : ROLE,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(foreignKey = ForeignKey(name = "fk_member_role_member_id"))
    var member : Member
)