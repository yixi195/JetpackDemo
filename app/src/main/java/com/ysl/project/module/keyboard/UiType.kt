package com.ysl.project.module.keyboard

import androidx.core.view.WindowInsetsCompat

object UiType {
    @JvmField
    val KEYBOARY = WindowInsetsCompat.Type.ime()
    @JvmField
    val ALL_BARS = WindowInsetsCompat.Type.systemBars()
    val STATUS_BAR = WindowInsetsCompat.Type.statusBars()
    val NAVIGATION_BAR = WindowInsetsCompat.Type.navigationBars()
}