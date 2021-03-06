package com.ysl.project.architecture

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.ysl.fastframe.base.fragment.BaseVMFragment
import com.ysl.project.R
import com.ysl.project.architecture.navigation.NavigationEgActivity
import com.ysl.project.architecture.paging.activity.PagingActivity
import com.ysl.project.architecture.workmanager.WorkerManagerActivity
import com.ysl.project.module.viewpager.ViewPager2ExampleActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : BaseVMFragment<DashboardViewModel>() {



    override fun getContentView(): Int {
        return R.layout.fragment_dashboard
    }

    override fun initView(view: View) {
        btn_navigation.setOnClickListener {
            startActivity(Intent(activity,NavigationEgActivity::class.java))
        }

        btn_workermanager.setOnClickListener {
            startActivity(Intent(activity,WorkerManagerActivity::class.java))
        }

        btn_paging.setOnClickListener {
            startActivity(Intent(activity,PagingActivity::class.java))
        }

        //viewpager2
        btn_viewpager2.setOnClickListener { startActivity(Intent(activity,ViewPager2ExampleActivity::class.java)) }
    }

    override fun providerVMClass() = DashboardViewModel::class.java
    override fun startObserve() {
        super.startObserve()
        mViewModel?.text.observe(this, Observer {
            text_dashboard.text = it
        })
    }
}