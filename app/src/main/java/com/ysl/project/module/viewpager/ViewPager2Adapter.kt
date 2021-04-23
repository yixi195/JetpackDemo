package com.ysl.project.module.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager2适配器
 *  Craete by YangShlai on 2021/4/23
 */
class ViewPager2Adapter(activity: AppCompatActivity,var list : ArrayList<ChildData>) : FragmentStateAdapter(activity) {

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

    fun setData(newList : List<ChildData>){
        //使用DiffUtil更新数据
        val callback = PagerDiffUtil(list,newList)
        //比对数据集之后，返回的差异结果 ，通过
        val diff = DiffUtil.calculateDiff(callback)
        list.clear()
        list.addAll(newList)
        // 根据diff 数据结果，选择刷新方式。
        diff.dispatchUpdatesTo(this)

        //总结：
        //1）实现DiffUtil.Callback   接口 ；
        // 2）新老数据集通过DiffUtil.calculateDiff 计算得到DiffUtil.DiffResult  ；
        // 3）DiffUtil.DiffResult::dispatchUpdatesTo()  刷新数据。
    }
}