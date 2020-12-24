package com.ysl.project.architecture.navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.ysl.fastframe.base.fragment.BaseFragment
import com.ysl.project.R
import kotlinx.android.synthetic.main.fragment_step_one.*

/**
 * Created by YangShlai on 2020/11/12.
 */
class StepOneFragment : BaseFragment() {
    override fun getContentView(): Int {
        return R.layout.fragment_step_one
    }

    override fun initView(view: View) {


        btn_skip.setOnClickListener {
            // 设置动画参数
            val navOption = navOptions {
                anim {
                    enter = R.anim.common_slide_in_right
                    exit = R.anim.common_slide_out_left
                    popEnter = R.anim.common_slide_in_left
                    popExit = R.anim.common_slide_out_right
                }
            }
            // 参数设置
            val bundle = Bundle()
            bundle.putString("name","传递的值是： test")
            findNavController().navigate(R.id.stepTwoFragment, bundle,navOption)
        }
    }

    override fun initData() {
    }
}