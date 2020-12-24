package com.ysl.project.architecture.navigation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.project.R

/**
 *
 * Created by YangShlai on 2020/11/12.
 */
class NavigationEgActivity : BaseActivity() {
    private var navHostFragment : NavHostFragment? = null
    private var navController : NavController? = null

    override fun getContentView(): Int {
        return R.layout.activity_navigation_eg
    }

    override fun initView() {
    }

    override fun initData() {
    }
}