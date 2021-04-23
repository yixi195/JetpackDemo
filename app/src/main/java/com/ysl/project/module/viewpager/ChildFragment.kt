package com.ysl.project.module.viewpager

import android.os.Bundle
import android.view.View
import com.ysl.fastframe.base.fragment.BaseFragment
import com.ysl.project.R
import kotlinx.android.synthetic.main.fragment_child.*
import java.util.logging.Logger

/**
 *  Craete by YangShlai on 2021/4/23
 */
class ChildFragment : BaseFragment() {
    override fun getContentView(): Int = R.layout.fragment_child

    companion object{
        fun newInstance(data : ChildData): ChildFragment{
            val args = Bundle()
            val fragment = ChildFragment()
            args.putSerializable("data",data)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(view: View) {
    }

    override fun initData() {
        var data = arguments?.getSerializable("data") as ChildData
        tv_content.text = data.id.toString() + "|" + data.content
    }
}