package com.ysl.project.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    var text: LiveData<String> = _text

}