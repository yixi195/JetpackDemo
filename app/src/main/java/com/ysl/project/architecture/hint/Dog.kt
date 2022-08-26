package com.ysl.project.architecture.hint

import android.util.Log
import javax.inject.Inject

/**
 *  Craete by YangShlai on 2021/6/10
 */
class Dog @Inject constructor() : Animal {
    override fun walking() {
        Log.d("ysl", "Dog 行走的狗狗")
    }

    override fun honking() {
        Log.d("ysl", "Dog 汪汪汪")
    }
}