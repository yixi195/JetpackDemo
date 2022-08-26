package com.ysl.project.home.view

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.ysl.fastframe.base.fragment.BaseVMFragment
import com.ysl.fastframe.utils.hilog.HiLog
import com.ysl.project.R
import com.ysl.project.coroutines.CoroutinesTestAcativity
import com.ysl.project.home.activity.HiLogActivity
import com.ysl.project.home.activity.ThreadTestActivity
import com.ysl.project.home.viewmodel.HomeViewModel
import com.ysl.project.test.activity.TestVMDatabindActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun providerVMClass() = HomeViewModel::class.java

    override fun getContentView(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        //网络请求示列
        btn_net.setOnClickListener { startActivity(Intent(activity, TestVMDatabindActivity::class.java)) }
        //改变 livedata 值
        btn_livedata.setOnClickListener {
            mViewModel.text.value = "改变后的值${Random(100).nextInt()}"
        }
        btn_coroutines.setOnClickListener {
            startActivity(Intent(activity,CoroutinesTestAcativity::class.java))
        }
        //hilog
        btn_hilog.setOnClickListener {
            startActivity(Intent(activity,HiLogActivity::class.java))
        }
        //thread
        btn_thread.setOnClickListener {
            startActivity(Intent(activity,ThreadTestActivity::class.java))
        }
    }

    /**
     * 监听
     */
    override fun startObserve() {
        super.startObserve()
        mViewModel.text.observe(this, Observer {
            btn_livedata.text = it
        })

    }

}