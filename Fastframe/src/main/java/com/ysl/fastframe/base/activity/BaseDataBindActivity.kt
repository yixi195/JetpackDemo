package com.ysl.fastframe.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindActivity<DB : ViewDataBinding> : BaseActivity() {

    lateinit var mDataBind: DB

    override fun setContentLayout() {
        mDataBind = DataBindingUtil.setContentView(this,getContentView())
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBind.unbind()
    }

}