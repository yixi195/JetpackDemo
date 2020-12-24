package com.ysl.fastframe.base

import com.ysl.fastframe.network.NetResponse
import com.ysl.fastframe.network.Result
import com.ysl.fastframe.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * 基础Repository
 * Created by YangShlai on 2019-11-12.
 */
open class BaseRepository {
    val TAG = "BaseRepository"

    /**
     * 回调
     */
    suspend fun <T : Any> apiCall(call: suspend () -> NetResponse<T>): NetResponse<T> {
        return call.invoke()
    }

    /**
     * try catch回调
     */
    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMsg: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            Result.Error(-1,errorMsg)
        }
    }

    /**
     * 处理返回结果
     */
    suspend fun <T : Any> excuteResponse(
        response: NetResponse<T>,
        successBlock : (suspend CoroutineScope.() -> Unit)? = null,
        erroBlock : (suspend CoroutineScope.() -> Unit)? = null) : Result<T>{
        return coroutineScope {
            if (response.code == Result.SUCCESS_CODE){
                Logger.i(TAG,"Result.Success()")
                successBlock?.let { it() }
                Result.Success(response.data)
            }else{
                Logger.i(TAG,"Result.Error()---")
                erroBlock?.let { it() }
                Result.Error(response.code,response.msg)
            }

        }
    }


}