package com.example.indukclubpromotionserver.member.entity

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

    @Column(nullable = false, length = 100)
    var password : String,
)