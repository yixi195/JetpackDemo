package com.ysl.fastframe.base.activity

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.ysl.fastframe.utils.*


/**
 * Created by YangShlai on 2019-09-19.
 */
abstract class BaseActivity : AppCompatActivity(),LifecycleObserver {
    protected var TAG = ""
    protected var dialogUtils: LoadingUtils? = null

    /** 初始化视图  */
    protected abstract fun getContentView(): Int

    /** 初始化布局  */
    protected abstract fun initView()

    /** 初始化数据  */
    protected abstract fun initData()


    override fun onCreate(savedInstanceState: Bundle?) {
        TAG = javaClass.simpleName
        Logger.i("BaseActivity", "onCreate开始时间===" + TimeUtils.nowMills)
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
//            val result = fixOrientation()
//            Logger.i("BaseActivity", "onCreate fixOrientation when Oreo, result = $result")
//        } else {
//            //强制竖屏
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        }
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        TAG = javaClass.simpleName
        setContentLayout()
        setStatusBar()
        ActivityManager.addActivity(this,TAG)
        AutoHideKeyboard.init(this)

        Logger.i("BaseActivity", "onCreate结束时间===" + TimeUtils.nowMills)
    }

    open fun setContentLayout(){
        setContentView(getContentView())
        initView()
        initData()
    }

    private fun createContentView(layoutResID: Int): View {
        val view = getLayoutInflater().inflate(layoutResID, null)
        return view
    }

    /**
     * 设置状态栏颜色背景
     */
    protected fun setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null) //需要去掉状态栏，单独在activity加上这一句
        BarTextColorUtils.setDarkStatusIcon(this, true)
    }

    fun showToast(text: String) {
        ToastUtils.show(text)
    }

    /**
     * 加载loading
     */
    fun showLoading(title: String) {
        dialogUtils = LoadingUtils(this)
        dialogUtils?.showLoading(title)
    }

    fun dismissLoading() {
        dialogUtils?.dismissLoading()
    }


    private fun fixOrientation(): Boolean {
        try {
            val field = Activity::class.java.getDeclaredField("mActivityInfo")
            field.isAccessible = true
            val o = field.get(this) as ActivityInfo
            o.screenOrientation = -1
            field.isAccessible = false
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            Logger.i(TAG, "avoid calling setRequestedOrientation when Oreo.")
            return
        }
        super.setRequestedOrientation(requestedOrientation)
    }

    /**
     * 适配 8.0 全透明 bug
     */
    private fun isTranslucentOrFloating(): Boolean {
        var isTranslucentOrFloating = false
        try {
            val styleableRes =
                Class.forName("com.android.internal.R\$styleable").getField("Window").get(null) as IntArray
            val ta = obtainStyledAttributes(styleableRes)
            val m = ActivityInfo::class.java.getMethod(
                "isTranslucentOrFloating",
                TypedArray::class.java
            )
            m.isAccessible = true
            isTranslucentOrFloating = m.invoke(null, ta) as Boolean
            m.isAccessible = false
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return isTranslucentOrFloating
    }
}