package com.ysl.fastframe.network

import com.ysl.fastframe.utils.Logger

import java.io.IOException

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request

/**
 * 日志打印
 * Created by YSL on 2017/5/4.
 */
class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body!!.contentType()
        val content = response.body!!.string()
        Logger.i(TAG, "\n")
        Logger.i(TAG, "----------Start----------------")
        Logger.i(TAG, "| $request")
        val method = request.method
        if ("POST" == method) {
            val sb = StringBuilder()
            if (request.body is FormBody) {
                val body = request.body as FormBody?
                for (i in 0 until body!!.size) {
                    sb.append(body.encodedName(i))
                        .append("=")
                        .append(body.encodedValue(i))
                        .append(",")
                }
                sb.delete(sb.length - 1, sb.length)
                Logger.i(TAG, "| RequestParams:{$sb}")
            }
        }
        Logger.i(TAG, "| Response:$content")
        Logger.i(TAG, "----------End:" + duration + "毫秒----------")
        return response.newBuilder()
            .body(okhttp3.ResponseBody.create(mediaType, content))
            .build()
    }

    companion object {
        var TAG = "LogInterceptor"
    }
}
