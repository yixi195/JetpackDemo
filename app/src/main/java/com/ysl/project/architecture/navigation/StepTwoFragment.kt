package com.ysl.project.architecture.navigation

import android.view.View
import androidx.navigation.fragment.findNavController
import com.ysl.fastframe.base.fragment.BaseFragment
import com.ysl.project.R
import kotlinx.android.synthetic.main.fragment_step_two.*

/**
 * Created by YangShlai on 2020/11/12.
 */
class StepTwoFragment : BaseFragment() {
    override fun getContentView(): Int {
        return R.layout.fragment_step_two
    }

    override fun initView(view: View) {
        tv_content.setText(arguments?.getString("name"))

        btn_skip.setOnClickListener {
            val action = StepTwoFragmentDirections
                .actionStepTwoFragmentToStepThreeFragment()
            findNavController().navigate(action)
        }
    }

    override fun initData() {
    }
}