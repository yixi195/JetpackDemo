package com.ysl.fastframe.network

import com.ysl.fastframe.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit
 */
abstract class BaseRetrofitClient {

    companion object {
        private const val TIME_OUT = 10
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = LogInterceptor()
                builder.addInterceptor(logging)
            }
            builder
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

            handleBuilder(builder)

            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}
