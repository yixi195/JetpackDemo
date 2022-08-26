package com.ysl.fastframe.utils.hilog

/**
 * Craete by YangShlai on 2021/6/10
 */
interface HiLogFormatter<T> {
    fun format(data: T): String?
}