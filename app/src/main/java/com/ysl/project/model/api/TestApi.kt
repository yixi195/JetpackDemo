package com.ysl.project.model.api

import com.ysl.fastframe.network.NetResponse
import com.ysl.project.model.bean.Banner
import com.ysl.project.model.bean.ArticleList
import retrofit2.http.*

/**
 * 测试接口
 * Created by YangShlai on 2019-11-12.
 */
interface TestApi {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    /**
     * 获取Banner列表
     */
    @GET("banner/json")
    suspend fun getBannerList() : NetResponse<List<Banner>>

    /**
     * 收藏
     */
    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    suspend fun shareArticle(@Field("title") title: String, @Field("link") url: String): NetResponse<String>


    /**
     * 获取最新文章列表
     */
    @GET("article/listproject/{page}/json")
    suspend fun getLastedProject(@Path("page") page: Int): NetResponse<ArticleList>
}