package com.ysl.project.module.keyboard.listener

interface KeyboardListener {
    fun onKeyBoardAnimStart()
    fun onKeyBoardHeightChange(height: Int)
    fun onKeyBoardAnimEnd()
}