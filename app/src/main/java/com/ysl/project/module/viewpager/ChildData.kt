package com.ysl.project.module.viewpager

import androidx.annotation.Keep
import java.io.Serializable

/**
 *  Craete by YangShlai on 2021/4/23
 */
@Keep
data class ChildData(
    var id : Long,
    var content : String
) : Serializable