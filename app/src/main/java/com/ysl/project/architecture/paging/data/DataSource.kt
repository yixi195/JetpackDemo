package com.ysl.project.architecture.paging.data

import androidx.paging.PagingSource
import com.ysl.fastframe.utils.Logger
import com.ysl.project.architecture.paging.repository.PagingRepository
import com.ysl.project.model.bean.WenDa

/**
 *  Craete by YangShlai on 2021/4/8
 */
class DataSource(): PagingSource<Int, WenDa>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WenDa> {
        return try {

            //页码未定义置为1
            var currentPage = params.key ?: 1
            //仓库层请求数据
            var demoReqData = PagingRepository().loadData(currentPage)
            //当前页码 小于 总页码 页面加1
            var nextPage = if (currentPage < demoReqData?.pageCount ?: 0) {
                currentPage + 1
            } else {
                //没有更多数据
                null
            }
            Logger.i("ysl","load===>" + nextPage)

            if (demoReqData != null) {
                LoadResult.Page(
                        data = demoReqData?.data,
                        prevKey = null,
                        nextKey = nextPage
                )
            } else {
                LoadResult.Error(throwable = Throwable())
            }


        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}