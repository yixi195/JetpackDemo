package com.ysl.fastframe.utils.hilog

/**
 *  Craete by YangShlai on 2021/6/10
 */
class HiThreadFormatter : HiLogFormatter<Thread>{
    override fun format(data: Thread): String? {
        return "Thread:${data.name}"
    }
}