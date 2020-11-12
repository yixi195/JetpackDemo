package com.ysl.fastframe.network

import com.google.gson.annotations.SerializedName


/**
 * 回调数据
 * Created by YangShlai on 2019-10-29.
 */
data class NetResponse<out T>(
    @SerializedName("errorCode")
    val code : Int,
    @SerializedName("errorMsg")
    val msg : String,
    val data: T
)