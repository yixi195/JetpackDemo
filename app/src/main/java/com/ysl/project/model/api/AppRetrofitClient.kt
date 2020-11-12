package com.ysl.project.model.api

import com.ysl.fastframe.network.BaseRetrofitClient
import com.ysl.fastframe.utils.NetWorkUtils
import com.ysl.project.App
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File

/**
 * 统一RetrofitClient管理
 * Created by YangShlai on 2019-11-12.
 */
object AppRetrofitClient : BaseRetrofitClient() {
    val service by lazy { getService(TestApi::class.java,TestApi.BASE_URL) }


    override fun handleBuilder(builder: OkHttpClient.Builder) {
        val httpCacheDirectory = File(App.insntance.cacheDir,"responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory,cacheSize)
        builder.cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetWorkUtils.isNetWorkAvailable(App.insntance)) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (!NetWorkUtils.isNetWorkAvailable(App.insntance)) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }

                response
            }
    }
}