package com.example.indukclubpromotionserver.promotions.dto

data class PromotionRequestDto (
    var id : Long?,
    var title : String,
    var content : String,
    var userId : Long,
    var club : String,
    var createAt : String,
    var closeAt : String,
    var begin : String,
    var end : String,
    var requiredPeople : Int,
    var contentOfActivity : String,
)

