package com.ysl.fastframe.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ysl.fastframe.utils.AutoHideKeyboard
import com.ysl.fastframe.utils.LoadingUtils


/**
 * Created by YangShlai on 2019-09-19.
 */
abstract class BaseFragment : Fragment() {
    protected var TAG = ""
    protected var mContentView: View? = null
    protected var dialogUtils: LoadingUtils? = null
    protected var isInitEnd = false //是否初始化完成

    /** 初始化视图  */
    protected abstract fun getContentView(): Int
    /** 初始化布局  */
    protected abstract fun initView(view: View)
    /** 初始化数据  */
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = javaClass.simpleName

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (this.mContentView == null) {
            this.mContentView = createContentView(getContentView())
        } else {
            val parent = this.mContentView?.getParent()
            parent?.let {
                (it as ViewGroup).removeView(this.mContentView)
            }
        }
        return this.mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!isInitEnd) {
            isInitEnd = true
            initView(this.mContentView!!)
            initData()
            AutoHideKeyboard.init(activity!!)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createContentView(layoutResID: Int): View {
        val view = getLayoutInflater().inflate(layoutResID, null)
        return view
    }

    /**
     * 加载loading
     */
    fun showLoading(title: String) {
        dialogUtils = context?.let { LoadingUtils(it) }
        dialogUtils?.showLoading(title)
    }
    fun dismissLoading() {
        dialogUtils?.dismissLoading()
    }
}