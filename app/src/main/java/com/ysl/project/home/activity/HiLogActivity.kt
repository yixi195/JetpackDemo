package com.ysl.project.home.activity

import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.fastframe.utils.hilog.HiLog
import com.ysl.project.R
import kotlinx.android.synthetic.main.activity_hilog.*

/**
 *  Craete by YangShlai on 2021/5/27
 */
class HiLogActivity : BaseActivity() {

    override fun getContentView(): Int = R.layout.activity_hilog

    override fun initView() {
        btn_ok.setOnClickListener {
            tv_msg.text = tv_msg.text.toString() + "\n" + "test"
            HiLog.v("88888")
            HiLog.a("99999");
        }
    }

    override fun initData() {

    }

}