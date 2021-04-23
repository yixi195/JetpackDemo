package com.ysl.project.module.viewpager

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.fastframe.utils.ToastUtils
import com.ysl.project.R
import kotlinx.android.synthetic.main.activity_viewpager2_example.*

/**
 * ViewPager2 示例
 *  Craete by YangShlai on 2021/4/23
 */
class ViewPager2ExampleActivity : BaseActivity(){
    override fun getContentView(): Int = R.layout.activity_viewpager2_example

    override fun initView() {
        var list = mutableListOf<ChildData>()
        list.add(ChildData(1,"first"))
        list.add(ChildData(2,"sencond"))
        list.add(ChildData(3,"three"))
        list.add(ChildData(4,"four"))
        list.add(ChildData(5,"five"))
        list.add(ChildData(6,"six"))
        var adapter = ViewPager2Adapter(this,list)
        vp.adapter = adapter

        //关联 tablayout
        TabLayoutMediator(tab_layout,vp){ tab, position ->
            tab.text = list[position].content + position
        }.attach()

        //viewpager2监听
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                ToastUtils.show("当前Page>>>${list[position].content}")
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    override fun initData() {
    }
}