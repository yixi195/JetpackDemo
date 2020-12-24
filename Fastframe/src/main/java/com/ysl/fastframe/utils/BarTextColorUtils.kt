package com.ysl.fastframe.utils

import android.R
import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import com.ysl.fastframe.utils.ScreenUtils.Companion.getStatusHeight

/**
 * 状态栏文字颜色
 */
object BarTextColorUtils {
    fun statusBarLightMode(activity: Activity): Int {
        var result = 0
        val window = activity.window
        //这个方法只支持4.0以上系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (MIUISetStatusBarLightMode(
                    activity.window,
                    true
                )
            ) { //判断是不是小米系统
                result = 1
            } else if (FlymeSetStatusBarLightMode(
                    activity.window,
                    true
                )
            ) { //判断是不是魅族系统
                result = 2
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = Color.WHITE
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                //  window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.setStatusBarColor(Color.WHITE);
            } else { //如果以上都不符合就只能加个半透明的背景色了
                result = 4
            }
        }
        return result
    }

    /**
     * 设置是否黑色转态栏
     * @param activity
     * @param bDark
     */
    fun setDarkStatusIcon(activity: Activity, bDark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = activity.window.decorView
            if (decorView != null) {
                var vis = decorView.systemUiVisibility
                vis = if (bDark) {
                    vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                decorView.systemUiVisibility = vis
            }
        }
    }

    //带有透明颜色的状态栏
    fun setTranslucentForCoordinatorLayout(activity: Activity, statusBarAlpha: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return
        }
        transparentStatusBar(activity) //先将状态栏设置为完全透明
        addTranslucentView(activity, statusBarAlpha) //添加一个自定义透明度的矩形状态栏
    }

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun transparentStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window
                .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            activity.window.statusBarColor = Color.TRANSPARENT
        } else {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 添加半透明矩形条
     *
     * @param activity       需要设置的 activity
     * @param statusBarAlpha 透明值
     */
    private fun addTranslucentView(activity: Activity, statusBarAlpha: Int) {
        val contentView =
            activity.findViewById<View>(R.id.content) as ViewGroup
        if (contentView.childCount > 1) {
            contentView.getChildAt(1)
                .setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0))
        } else {
            contentView.setPadding(0, getStatusHeight(activity), 0, 0)
        }
    }

    /**
     * 创建半透明矩形 View
     *
     * @param alpha 透明值
     * @return 半透明 View
     */
    private fun createTranslucentStatusBarView(activity: Activity, alpha: Int): View {
        // 绘制一个和状态栏一样高的矩形
        val statusBarView = View(activity)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            getStatusHeight(activity)
        )
        statusBarView.layoutParams = params
        statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0))
        return statusBarView
    }

    /**
     * 修改小米手机系统的
     */
    private fun MIUISetStatusBarLightMode(
        window: Window?,
        dark: Boolean
    ): Boolean {
        var result = false
        if (window != null) {
            val clazz: Class<*> = window.javaClass
            try {
                var darkModeFlag = 0
                val layoutParams =
                    Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field =
                    layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag) //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag) //清除黑色字体
                }
                result = true
            } catch (ignored: Exception) {
            }
        }
        return result
    }

    /**
     * 魅族手机修改该字体颜色
     */
    private fun FlymeSetStatusBarLightMode(
        window: Window?,
        dark: Boolean
    ): Boolean {
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = if (dark) {
                    value or bit
                } else {
                    value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (ignored: Exception) {
            }
        }
        return result
    }
}