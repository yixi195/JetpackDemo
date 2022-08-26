package com.ysl.project.architecture.koin.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 *  Create by YangShlai on 2022/8/25
 */
val netWorkModule = module {
    single {
        OkHttpClient().newBuilder()
            .connectTimeout(88, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://www.koin.com/")
            .build()
    }

}