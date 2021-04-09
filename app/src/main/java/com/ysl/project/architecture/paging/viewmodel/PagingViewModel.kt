package com.ysl.project.architecture.paging.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ysl.fastframe.base.viewmodel.BaseViewModel
import com.ysl.project.architecture.paging.data.DataSource

/**
 *  Craete by YangShlai on 2021/3/26
 */
class PagingViewModel : BaseViewModel() {
    /**
     * 获取数据
     *
     * PagingConfig中的参数如下：
    pageSize:定义从 PagingSource 一次加载的项目数。
    prefetchDistance:预取距离，简单解释就是 当距离底部还有多远的时候自动加载下一页，即自动调用load方法，默认值和pageSize相等
    enablePlaceholders：是否显示占位符，当网络不好的时候，可以考到页面的框架，从而提升用户体验
     */
    fun getData() = Pager(PagingConfig(pageSize = 1,prefetchDistance = 5)) {
        DataSource()
    }.flow
}