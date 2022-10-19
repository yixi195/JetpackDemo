package com.ysl.project.other

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ysl.fastframe.base.viewmodel.BaseViewModel

class NotificationsViewModel : BaseViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    var text: LiveData<String> = _text

}