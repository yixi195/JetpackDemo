package com.ysl.fastframe.base.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.net.SocketException

/**
 * 基本ViewModel
 * Created by YangShlai on 2019-09-19.
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {
    //通用事件模型驱动(如：显示对话框、取消对话框、错误提示)
    val mStateLiveData = MutableLiveData<StateActionEvent>()


    /**
     * 通用使用协程封装统一的请求处理
     */
    fun launch(block: suspend CoroutineScope.() -> Unit) {
        //会在页面销毁的时候自动取消请求，不过必须要使用AndroidX,
        viewModelScope.launch{
            block()
        }
    }

    /**
     * LiveData发射数据
     */
    fun <T> emit(block: suspend LiveDataScope<T>.() -> T): LiveData<T> = liveData {
        try {
            emit(block())
        } catch (e: Exception) {
            mStateLiveData.value = ErrorState(e.message)
        }
    }

    /**
     * LiveData发射数据-带UI状态
     */
    fun <T> emitOnState(block: suspend LiveDataScope<T>.() -> T): LiveData<T> = liveData {
        try {
            mStateLiveData.value = LoadState
            emit(block())
            mStateLiveData.value = SuccessState
        } catch (e: Exception) {
            var erroMsg = e.message
            if (e is SocketException){
                erroMsg = "网络连接失败"
            }
            mStateLiveData.value = ErrorState(erroMsg)
        }
    }

    suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }

    fun launchTry(tryBlock: suspend CoroutineScope.() -> Unit) {
        launch {
            tryCatch(tryBlock, {}, {}, true)
        }
    }

    fun launchOnUITryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean
    ) {
        launch {
            tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellationExceptionManually)
        }
    }

    fun launchOnUITryCatch(tryBlock: suspend CoroutineScope.() -> Unit,
                           handleCancellationExceptionManually: Boolean) {
        launch {
            tryCatch(tryBlock,{},{},handleCancellationExceptionManually)
        }
    }


    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean = false
    ) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Throwable) {
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    mStateLiveData.value = ErrorState(e.message)
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }
}
