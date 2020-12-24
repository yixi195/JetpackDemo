package com.ysl.project.home.repository

import com.ysl.fastframe.base.BaseRepository
import com.ysl.fastframe.network.Result
import com.ysl.project.model.api.AppRetrofitClient
import com.ysl.project.model.bean.Banner
import com.ysl.project.model.bean.ArticleList

/**
 * 首页仓库
 * Created by YangShlai on 2019-11-12.
 */
class HomeRepository : BaseRepository() {
    suspend fun getBanners(): Result<List<Banner>> =
        excuteResponse(AppRetrofitClient.service.getBannerList())

    suspend fun addCollect(title : String,url : String)  : Result<String> =
        excuteResponse(AppRetrofitClient.service.shareArticle(title, url))

    suspend fun getLastedProject(page: Int) : Result<ArticleList> =
        excuteResponse(AppRetrofitClient.service.getLastedProject(page))
}