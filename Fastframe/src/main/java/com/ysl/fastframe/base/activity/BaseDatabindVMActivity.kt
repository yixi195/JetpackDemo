package com.ysl.fastframe.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.ysl.fastframe.base.viewmodel.BaseViewModel

/**
 * Created by YangShlai on 2019-10-23.
 */
abstract class BaseDatabindVMActivity<DB : ViewDataBinding,VM : BaseViewModel> : BaseVMActivity<VM>() {
    lateinit var mDataBind : DB

    override fun setContentLayout() {
        mDataBind = DataBindingUtil.setContentView(this,getContentView())
        mDataBind.lifecycleOwner = this
        initView()
        initData()
    }


    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDataBind.unbind()
    }
}