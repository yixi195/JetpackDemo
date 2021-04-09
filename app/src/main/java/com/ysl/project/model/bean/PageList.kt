package com.ysl.project.model.bean

import com.google.gson.annotations.SerializedName

/**
 *  Craete by YangShlai on 2021/4/8
 */
data class PageList<T>(
        @SerializedName("datas")
        val data: List<T>,
        val curPage: Int,
        val offset: Int,
        val pageCount: Int,
        val over: Boolean,
        val size: Int,
        val total: Long
)