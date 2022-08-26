package com.fenlai.fastframe.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ysl.fastframe.base.fragment.BaseFragment

/**
 * 基础 databind Fragment
 * Created by YangShlai on 2021-07-22.
 */
abstract class BaseDataBindFragment<DB : ViewDataBinding> : BaseFragment() {

    lateinit var mDataBind: DB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (mDataBind == null || mContentView == null) {
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