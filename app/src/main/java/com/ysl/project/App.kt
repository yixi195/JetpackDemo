package com.ysl.project

import android.app.Application
import com.ysl.fastframe.base.BaseApplication
import kotlin.properties.Delegates

/**
 * 入口
 * Created by YangShlai on 2019-11-12.
 */
class App : BaseApplication() {
    companion object{
        var insntance : Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        insntance = this
    }
}