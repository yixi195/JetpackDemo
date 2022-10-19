package com.ysl.project

import android.app.Application
import android.util.DisplayMetrics
import com.ysl.fastframe.base.BaseApplication
import com.ysl.fastframe.utils.hilog.HiLog
import com.ysl.fastframe.utils.hilog.HiLogConfig
import com.ysl.fastframe.utils.hilog.HiLogManager
import com.ysl.project.architecture.hint.HiltTest
import com.ysl.project.architecture.koin.di.netWorkModule
import com.ysl.project.architecture.koin.di.testModule
import com.ysl.project.module.keyboard.SystemUiControll
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.logging.Logger
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * 入口
 * Created by YangShlai on 2019-11-12.
 */
@HiltAndroidApp
class App : BaseApplication() {
    @Inject
    lateinit var hiltTest : HiltTest

    companion object{
        var insntance : Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        insntance = this
        hiltTest.print()

        HiLogManager.init(object : HiLogConfig(){
            override fun getGlobalTag(): String {
                return "YSL"
            }

            override fun enable(): Boolean {
                return BuildConfig.DEBUG
            }
        })
        val smallestScreenWidthDp = resources.configuration.smallestScreenWidthDp
        HiLog.vt("ysl","base_dpi:" + resources.getString(R.string.base_dpi) + "|sw:" + smallestScreenWidthDp)



        //koin注入
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            androidFileProperties() //允许读取 默认名字为koin.properties,你也可以直接重新设置名称
            modules(netWorkModule,testModule) //加载注入的module
        }

        //keyboard
        SystemUiControll.getInstence().register(this@App)
    }
}