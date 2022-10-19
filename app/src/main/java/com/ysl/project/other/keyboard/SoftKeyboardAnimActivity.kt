package com.ysl.project.other.keyboard

import androidx.core.view.WindowCompat
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.project.R
import com.ysl.project.module.keyboard.SystemUiControll
import kotlinx.android.synthetic.main.activity_soft_keyboard.*

/**
 *  Create by YangShlai on 2022/10/19
 */
class SoftKeyboardAnimActivity : BaseActivity() {
    override fun getContentView(): Int = R.layout.activity_soft_keyboard

    override fun initView() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        SystemUiControll.instence.setAutoMoveView(ll_et)
    }

    override fun initData() {
    }
}