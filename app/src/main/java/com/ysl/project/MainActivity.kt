package com.ysl.project

import android.os.Bundle
import android.util.DisplayMetrics
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ysl.fastframe.utils.hilog.HiLog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //获取屏幕分辨率
        val metric = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        val width = metric.widthPixels // 宽度（PX）
        val height = metric.heightPixels // 高度（PX）
        val density = metric.density // 密度（0.75 / 1.0 / 1.5）
        val densityDpi = metric.densityDpi // 密度DPI（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        val screenWidth = (width / density).toInt() //屏幕宽度(dp)
        val screenHeight = (height / density).toInt() //屏幕高度(dp)
        val a = """宽度:$width 高度:$height 密度:$density 密度DPI:$densityDpi
屏幕dp宽度：$screenWidth 屏幕dp高度：$screenHeight"""
        HiLog.vt("ysl","屏幕信息===>$a")
    }
}
