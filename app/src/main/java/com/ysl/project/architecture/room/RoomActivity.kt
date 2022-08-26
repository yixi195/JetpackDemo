package com.ysl.project.architecture.room

import androidx.lifecycle.viewModelScope
import com.ysl.fastframe.base.activity.BaseDatabindVMActivity
import com.ysl.project.R
import com.ysl.project.databinding.ActivityRoomBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Room 数据库示范
 *  Craete by YangShlai on 2021/6/22
 */
class RoomActivity : BaseDatabindVMActivity<ActivityRoomBinding,RoomViewModel>() {
    private var mAdapter : RoomAdapter? = null

    override fun getContentView(): Int = R.layout.activity_room

    override fun providerVMClass() = RoomViewModel::class.java

    override fun initView() {
        mAdapter = RoomAdapter(this)
        mDataBind.rv.adapter = mAdapter

        mDataBind.btnAdd.setOnClickListener {
            var brand = mDataBind.etBrand.text.toString()
            var price = mDataBind.etPrice.text.toString()
            if (brand.isNullOrEmpty() || price.isNullOrEmpty()){
                showToast("不能为空！")
                return@setOnClickListener
            }

            mViewModel.addItem(brand,price.toInt())
            showToast("添加成功")
        }

        mDataBind.btnQuery.setOnClickListener {
            mViewModel.launchIO {
                var list = mViewModel.queryAll()
                withContext(Dispatchers.Main){
                    mAdapter?.setDataList(list)
                }
            }
        }

        //删除
        mAdapter?.delListener = { pos,data->
            var id = -1
            mViewModel.launchIO {
                id = mViewModel.delItem(data)!!
                withContext(Dispatchers.Main){
                    showToast("删除成功$id")
                    mDataBind.btnQuery.performClick()
                }
            }

        }
    }

    override fun initData() {

    }
}