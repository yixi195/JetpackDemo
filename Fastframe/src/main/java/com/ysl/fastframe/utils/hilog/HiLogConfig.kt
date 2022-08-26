package com.ysl.fastframe.utils.hilog

/**
 *  Craete by YangShlai on 2021/5/27
 */
open class HiLogConfig {
    var MAX_LEN = 512
    var HI_STACK_TRACE_FORMATTER = HiStackTraceFormatter()
    var HI_THREAD_FORMATTER = HiThreadFormatter()
    open fun injectJsonParser() : JsonParser? = null

    open fun getGlobalTag() = "HiLog"
    open fun enable() = true
    open fun includeThread() = false
    open fun stackTraceDepth() = 5
    var printers : Array<HiLogPrinter>? = null

    interface JsonParser{
        fun toJson(src : Any) : String
    }
}