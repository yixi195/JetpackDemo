package com.ysl.fastframe.base.activity

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.ysl.fastframe.base.viewmodel.BaseViewModel
import com.ysl.fastframe.base.viewmodel.ErrorState
import com.ysl.fastframe.base.viewmodel.LoadState
import com.ysl.fastframe.base.viewmodel.SuccessState
import kotlinx.coroutines.launch

/**
 * Created by YangShlai on 2019-10-23.
 */
abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()
    }

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            mViewModel.let(lifecycle::addObserver)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    /**
     * 开始监听
     */
    open fun startObserve() {
        //状态过程
        mViewModel?.mStateLiveData.observe(this, Observer {
            when(it){
                LoadState -> showLoading("")
                SuccessState -> dismissLoading()
                is ErrorState -> {
                    dismissLoading()
                    it.message?.apply {
                        showToast(this)
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
//        mViewModel.let(lifecycle::removeObserver)
        super.onDestroy()
    }
}