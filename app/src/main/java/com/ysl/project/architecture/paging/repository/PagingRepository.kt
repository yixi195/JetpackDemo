package com.ysl.project.architecture.paging.repository

import com.ysl.fastframe.base.BaseRepository
import com.ysl.fastframe.network.Result
import com.ysl.fastframe.utils.ToastUtils
import com.ysl.project.model.api.AppRetrofitClient
import com.ysl.project.model.bean.PageList
import com.ysl.project.model.bean.WenDa
import java.lang.Exception

/**
 * 分页仓库
 *  Craete by YangShlai on 2021/3/26
 */
class PagingRepository : BaseRepository() {

    /**
     * 加载数据
     */
    suspend fun loadData(page : Int) : PageList<WenDa>? {
        return try {
            var result = excuteResponse(AppRetrofitClient.service.getWenDaList(page))
            if (result is Result.Success){
                return result.data
            }else if (result is Result.Error){
                ToastUtils.show(result.errMsg)
            }
            return null
        }catch (e : Exception){
            null
        }
    }

}