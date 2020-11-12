package com.ysl.project.ui.home

import androidx.lifecycle.MutableLiveData
import com.ysl.fastframe.base.viewmodel.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "JectPack网络请求示列"
    }
    val text: MutableLiveData<String> = _text
}