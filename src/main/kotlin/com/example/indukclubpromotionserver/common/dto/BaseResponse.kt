package com.example.indukclubpromotionserver.common.dto

import com.example.indukclubpromotionserver.common.status.ResultCode

data class BaseResponse<T>(
    val resultCode : String = ResultCode.SUCCESS.name,
    val data : T? = null,
    val message : String = ResultCode.SUCCESS.msg,
)