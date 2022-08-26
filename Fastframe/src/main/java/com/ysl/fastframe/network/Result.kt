package com.ysl.fastframe.network

/**
 * 密封 result
 * Created by YangShlai on 2019-11-12.
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data : T) : Result<T>()
    data class Error(val errCode : Int,val errMsg : String) : Result<Nothing>()

    companion object{
        const val SUCCESS_CODE = 0
    }

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errMsg]"
        }
    }

}