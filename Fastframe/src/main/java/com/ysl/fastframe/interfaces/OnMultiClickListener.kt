package com.ysl.fastframe.interfaces

import android.view.View

/**
 * 防止重复点击类
 * Created by YangShlai on 2019/1/9.
 */
abstract class OnMultiClickListener : View.OnClickListener {
    abstract fun onMultiClick(v: View?)
    override fun onClick(v: View) {
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime
            onMultiClick(v)
        }
    }

    companion object {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private const val MIN_CLICK_DELAY_TIME = 1000
        private var lastClickTime: Long = 0
    }
}