package com.ysl.project.architecture.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ysl.fastframe.utils.Logger
import com.ysl.project.R
import com.ysl.project.databinding.ItemWendaBinding
import com.ysl.project.model.bean.WenDa

/**
 *  Craete by YangShlai on 2021/3/26
 */
class PagingAdapter : PagingDataAdapter<WenDa, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<WenDa>(){
    private val TAG = "PagingAdapter"

    override fun areItemsTheSame(oldItem: WenDa, newItem: WenDa): Boolean {
        Logger.i(TAG,"areItemsTheSame--->" + oldItem.id + "|" + newItem.id)
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WenDa, newItem: WenDa): Boolean {
        Logger.i(TAG,"areContentsTheSame===>" + oldItem.id + "|" + newItem.id)
        return oldItem == newItem
    }

}) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Logger.i("PagingAdapter","onBindViewHolder===>" + position)
        var dataBean = getItem(position)
        (holder as DataViewHolder).binding.data = dataBean
//        holder.binding.btnUpdate.setOnClickListener {
//            itemUpdate(position, dataBean,this)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemWendaBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_wenda,
                parent,
                false
        )
        return DataViewHolder(binding)
    }

    inner class DataViewHolder(private val dataBindingUtil: ItemWendaBinding) :
            RecyclerView.ViewHolder(dataBindingUtil.root) {
        var binding = dataBindingUtil
    }
}