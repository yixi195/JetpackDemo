package com.ysl.fastframe.base.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ysl.fastframe.base.viewmodel.BaseViewModel

/**
 *
 * Created by YangShlai on 2019-10-23.
 */
abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {

    protected lateinit var mViewModel: VM

    override fun initData() {
        initVM()
        startObserve()
    }

    open fun startObserve() {

    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(mViewModel)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
}