package com.ysl.project.module.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager2适配器
 *  Craete by YangShlai on 2021/4/23
 */
class ViewPager2Adapter(activity: AppCompatActivity,val list : List<ChildData>) : FragmentStateAdapter(activity) {

    //或构造函数
//    constructor(activity: AppCompatActivity,list : List<ChildData>) : super(activity){
//
//    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ChildFragment.newInstance(list[position])
    }
}