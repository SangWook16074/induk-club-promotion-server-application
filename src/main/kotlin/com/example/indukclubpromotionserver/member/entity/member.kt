package com.example.indukclubpromotionserver.member.entity

import com.example.indukclubpromotionserver.common.status.ROLE
import com.example.indukclubpromotionserver.member.dto.ClubInfoResponseDto
import com.example.indukclubpromotionserver.member.dto.MemberResponseDto
import jakarta.persistence.*
import java.time.LocalDateTime

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    val club : ClubInfo? = null

    fun toResponse() : MemberResponseDto = MemberResponseDto(
        id = id!!,
        name = name,
        clubInfo = club?.toResponse(),

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

@Entity
class ClubInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long?,

    @Column(nullable = false, length = 100)
    var clubName : String,

    @Column(nullable = false, length = 100)
    var createAt : LocalDateTime,

    @Column(nullable = false, length = 100)
    var classify : String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_club_member_id"))
    var member: Member
) {
    fun toResponse() : ClubInfoResponseDto = ClubInfoResponseDto(
        id = id,
        clubName = clubName,
        createAt = createAt,
        classify = classify,
    )
}