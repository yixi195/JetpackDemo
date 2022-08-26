package com.ysl.project.architecture.hint.module

import com.ysl.fastframe.utils.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * 我们希望刚才在HttpModule中提供的OkHttpClient实例全项目使用，可以这样修改
 * ActivityComponent::class 作用域为 Activity 范围
 * @InstallIn(ApplicationComponent::class)  作用域 Application 范围
 * 只有作用域为Application 才可以使用@Singleton
 *  Craete by YangShlai on 2021/6/10
 */
@Module
@InstallIn(ApplicationComponent::class) //注解指定 module 的范围,此处为 Activity
class HttpModule {

    //常用于被 @Module 注解标记类的内部方法上。并提供依赖项对象。
    /**
     * @Inject
       lateinit var okHttpClient: OkHttpClient
    具体可以参考 HiltSamActiviy.kt */
    @Singleton //全局单例
    @Provides
    fun providesOkhttpClient(): OkHttpClient {
        Logger.i("ysl", "providesOkhttpClient===")
        return OkHttpClient().newBuilder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    /**
     * 测试注入 Retrofit 提供给别的地方注入引用
     * @Inject
     * lateinit var retrofit : Retrofit
     * 具体可以参考 HiltSamActiviy.kt
     */
    @Provides
    fun providesRetrofitClient(): Retrofit {
        Logger.i("ysl", "providesRetrofitClient===")
        return Retrofit.Builder()
            .baseUrl("http://www.hilt.com/")
            .build()
    }

    //关于Context
//    class UserMsg @Inject constructor(@ActivityContext val context: Context) {
//
//    }
//    class UserMsg @Inject constructor(@ApplicationContext val context: Context) {
//
//    }
}