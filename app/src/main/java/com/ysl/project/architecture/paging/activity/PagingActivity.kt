package com.ysl.project.architecture.paging.activity

import androidx.lifecycle.lifecycleScope
import com.ysl.fastframe.base.activity.BaseDatabindVMActivity
import com.ysl.fastframe.utils.Logger
import com.ysl.project.R
import com.ysl.project.architecture.paging.adapter.PagingAdapter
import com.ysl.project.architecture.paging.viewmodel.PagingViewModel
import com.ysl.project.databinding.ActivityPagingBinding
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *  分页Paging3
 *  Craete by YangShlai on 2021/3/26
 */
class PagingActivity : BaseDatabindVMActivity<ActivityPagingBinding,PagingViewModel>(){
    private lateinit var adapter : PagingAdapter

    override fun getContentView(): Int = R.layout.activity_paging

    override fun initView() {
        adapter = PagingAdapter()
        rv.adapter = adapter
        btn_query.setOnClickListener {
            lifecycleScope.launch {
                mViewModel.getData().collectLatest {
                    Logger.i("ysl","collectLatest--->")
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun providerVMClass() = PagingViewModel::class.java

    override fun initData() {
    }
}