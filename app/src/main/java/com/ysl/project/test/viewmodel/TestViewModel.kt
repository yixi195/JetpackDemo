package com.ysl.project.test.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.ysl.fastframe.base.viewmodel.BaseViewModel
import com.ysl.fastframe.network.Result
import com.ysl.fastframe.utils.Logger
import com.ysl.project.home.repository.HomeRepository
import com.ysl.project.model.bean.ArticleList
import com.ysl.project.model.bean.Banner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by YangShlai on 2019-11-12.
 */
class TestViewModel : BaseViewModel() {
    private val TAG = "TestViewModel"

    val strObser = ObservableField("123")
    private val homeRepository by lazy { HomeRepository() }

    private val _uiState = MutableLiveData<TestUiModel>()
    val uiState : LiveData<TestUiModel>
        get() = _uiState


    /**
     * onResum 生命周期 二选一
     * 1.不带LifecycleOwner参数
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        //todo
        Logger.i(TAG,"onResume()----")
    }

    //2.带LifecycleOwner参数
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(lifecycleOwner: LifecycleOwner) {
        //todo
        Logger.i(TAG,"onResume()----" + lifecycleOwner.lifecycle.currentState)
    }

    /**
     * banner
     */
    val mBanners : LiveData<List<Banner>> = liveData {
        kotlin.runCatching {
            val data = homeRepository.getBanners()

            if (data is Result.Success) emit(data.data)
            if (data is Result.Error) data.errMsg
        }
    }

    /**
     * 添加收藏
     */
    fun addCollect(title: String, url: String) {
        viewModelScope.launch {
            if (title.isBlank() || url.isBlank()) {
                emitUiState(showError = "不能为空！")
                return@launch
            }

            withContext(Dispatchers.Main) { emitUiState(true) }
            var result = homeRepository.addCollect(title, url)
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    emitUiState(showSuccess = result.data)
                } else if (result is Result.Error) {
                    emitUiState(showError = result.errMsg)
                }
            }
        }
    }

    fun addCollectByLiveData(title: String, url: String) : LiveData<Result<String>> = emitOnState {
        homeRepository.addCollect(title, url)
    }

    /**
     * 获取最新文章列表
     */
    fun getArticleList(page : Int) : LiveData<Result<ArticleList>> = emit {
        homeRepository.getLastedProject(page)
    }

    /**
     * 更新UI状态
     */
    private fun emitUiState(
        showProcess: Boolean = false,
        showSuccess: Any? = null,
        showError: String? = null
    ) {
        val uiModel = TestUiModel(
            showProcess,
            showSuccess,
            showError
        )
        _uiState.value = uiModel

    }
}

/**
 * 界面状态
 */
data class TestUiModel(
    val showProcess: Boolean,
    val showSuccess: Any?,
    val showError: String?
)
