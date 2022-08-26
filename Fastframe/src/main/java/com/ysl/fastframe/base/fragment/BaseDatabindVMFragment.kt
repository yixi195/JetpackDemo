package com.fenlai.fastframe.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ysl.fastframe.base.fragment.BaseVMFragment
import com.ysl.fastframe.base.viewmodel.BaseViewModel

/**
 * 基础 databind+viewmodel Fragment
 * Created by YangShlai on 2021-07-22.
 */
abstract class BaseDatabindVMFragment<DB : ViewDataBinding,VM : BaseViewModel> : BaseVMFragment<VM>() {
    lateinit var mDataBind : DB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (mContentView == null) {
            this.mDataBind = DataBindingUtil.inflate(inflater,getContentView(),container,false)
            this.mContentView = mDataBind.root
        } else {
            val parent = this.mContentView?.getParent()
            parent?.let {
                (it as ViewGroup).removeView(this.mContentView)
            }
        }
        mDataBind.lifecycleOwner = this
        return this.mContentView
    }


    override fun onDestroy() {
        super.onDestroy()
        mDataBind.unbind()
    }
}