package com.ysl.project.architecture.hint

import android.util.Log
import javax.inject.Inject

/**
 *  Craete by YangShlai on 2021/6/10
 */
class Snake @Inject constructor() : Animal {
    override fun walking() {
        Log.d("ysl", "Snake 滑滑滑滑滑")
    }

    override fun honking() {
        Log.d("ysl", "Snake 咕咕咕咕")
    }
}