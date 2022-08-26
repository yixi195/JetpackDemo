package com.ysl.project.architecture.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ysl.project.R

/**
 *  Craete by YangShlai on 2021/6/22
 */
class RoomAdapter : RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private lateinit var mContext : Context
    private var list : List<Car> = arrayListOf()
    var delListener : ((pos : Int,car : Car)->Unit)? = null

    constructor(context : Context){
        this.mContext = context
    }

    fun setDataList(list : List<Car>?){
        list?.let {
            this.list = it
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_room,parent,false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        var data = list[position]
        holder.tvContent.text = "序号:${data.id}\t品牌:${data.brand}\t价格:${data.price}\t年龄:${data.age}"
        holder.btnDel.setOnClickListener {
            delListener?.invoke(position,data)
        }
    }

    class RoomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvContent = itemView.findViewById<TextView>(R.id.tv_content)
        var btnDel = itemView.findViewById<Button>(R.id.btn_del)
    }
}