package com.example.indukclubpromotionserver.promotions.dto

data class PromotionRequestDto (
    var title : String,
    var content : String,
    var club : String,
    var createAt : String,
    var closeAt : String,
    var begin : String,
    var end : String,
    var requiredPeople : Int,
    var contentOfActivity : String,
    var url : String?,
    var images : List<String>,
)