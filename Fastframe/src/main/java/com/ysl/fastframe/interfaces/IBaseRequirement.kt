package com.ysl.fastframe.interfaces

import okhttp3.Interceptor

/**
 * Created by huang on 2017/5/15.
 *
 * 此接口的功能是提供本框架所需要的提供一些基本的东西。
 * 1.getUrl()            服务器地址
 * 2.getInterceptors()   如果有自定义的跟业务有关的网络拦截器，添加到这里来
 * 3.getActivityHelper() 继承IActivityHelper接口，用于对 BaseActivityEx 进行扩展
 * 4.getFragmentHelper() 继承IFragmentHelper接口，用于对 BaseFragment 进行扩展
 * 5.getImageCacheDir    图片缓存目录，本框架使用Glide显示图片
 * --待续
 *
 */
interface IBaseRequirement {
    val url : String
    val interceptors: List<Interceptor>
    val activityHelper : IActivityHelper
    val fragmentHelper : IFragmentHelper
    val imageCacheDir : String
    /**
     * 崩溃闪退日志目录
     */
    val crashLogDir: String

}
