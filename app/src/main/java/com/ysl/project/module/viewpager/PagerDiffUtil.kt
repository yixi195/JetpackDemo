package com.ysl.project.module.viewpager

import androidx.recyclerview.widget.DiffUtil

/**
 *  Craete by YangShlai on 2021/4/23
 */
class PagerDiffUtil(val oldList : List<ChildData>,val newList : List<ChildData>) : DiffUtil.Callback() {

    enum class PayloadKey {
        VALUE
    }

    //旧数据集的长度；
    override fun getOldListSize(): Int = oldList.size

    //新数据集的长度
    override fun getNewListSize(): Int = newList.size

    //判断是否是同一个item；
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    //如果item相同，此方法用于判断是否同一个 Item 的内容也相同；
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].content.equals(newList[newItemPosition].content)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return listOf(PayloadKey.VALUE)
    }
}