package com.ysl.fastframe.utils.hilog

/**
 *  Craete by YangShlai on 2021/6/10
 */
interface HiLogPrinter {
    fun print(config: HiLogConfig,level : Int,tag : String,content : String)
}