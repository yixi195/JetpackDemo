package com.ysl.project.ui.home

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.ysl.fastframe.base.fragment.BaseVMFragment
import com.ysl.project.R
import com.ysl.project.test.activity.TestVMDatabindActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun providerVMClass() = HomeViewModel::class.java

    override fun getContentView(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        text_home.setOnClickListener { startActivity(Intent(activity, TestVMDatabindActivity::class.java)) }
        tv_livedata.setOnClickListener {
            mViewModel.text.value = "改变后的值${Random(100)}"
        }
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.text.observe(this, Observer {
            text_home.text = it
        })

    }

}