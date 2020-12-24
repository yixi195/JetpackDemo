package com.ysl.fastframe.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * 基础入口
 * Created by YangShlai on 2019-11-13.
 */
open class BaseApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        //初始化基础业务支撑
        BaseInitHelper.instance.applicationContext = applicationContext

    }
}