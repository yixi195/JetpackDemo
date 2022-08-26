package com.ysl.fastframe.utils.hilog

import android.util.Log

/**
 *  Craete by YangShlai on 2021/5/27
 */
object HiLog {

    //在数组类型前面添加 * ，可以传递给可变参数
    fun v(vararg contents: Any?) {
        log(HiLogType.V, *contents)
    }

    fun vt(tag: String?, vararg contents: Any?) {
        log(HiLogType.V, tag, *contents)
    }

    fun d(vararg contents: Any?) {
        log(HiLogType.D, *contents)
    }

    fun dt(tag: String?, vararg contents: Any?) {
        log(HiLogType.D, tag, *contents)
    }

    fun i(vararg contents: Any?) {
        log(HiLogType.I, *contents)
    }

    fun it(tag: String?, vararg contents: Any?) {
        log(HiLogType.I, tag, *contents)
    }

    fun w(vararg contents: Any?) {
        log(HiLogType.W, *contents)
    }

    fun wt(tag: String?, vararg contents: Any?) {
        log(HiLogType.W, tag, *contents)
    }

    fun e(vararg contents: Any?) {
        log(HiLogType.E, *contents)
    }

    fun et(tag: String?, vararg contents: Any?) {
        log(HiLogType.E, tag, *contents)
    }

    fun a(vararg contents: Any?) {
        log(HiLogType.A, *contents)
    }

    fun at(tag: String?, vararg contents: Any?) {
        log(HiLogType.A, tag, *contents)
    }


    fun log(type: Int, vararg contents: Any?) {
        log(type, HiLogManager.instance.config.getGlobalTag(), *contents)
    }

    fun log(type: Int, tag: String?, vararg contents: Any?) {
        log(HiLogManager.instance.config, type, tag, *contents)
    }

    fun log(config: HiLogConfig, type: Int, tag: String?, vararg contents : Any?) {
        if (!config.enable()) return
        var sb = StringBuffer()
        var body = parseBody(contents)
        sb.append(body)
        Log.println(type, tag, body)
    }

    private fun parseBody(vararg contents: Array<out Any?>): String {
        val sb = StringBuffer()
        for (o in contents) {
            sb.append(o[0].toString()).append(";")
        }
        if (sb.length > 0) sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }
}