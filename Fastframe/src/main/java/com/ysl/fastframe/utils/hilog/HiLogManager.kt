package com.ysl.fastframe.utils.hilog

import org.jetbrains.annotations.NotNull

/**
 *  Craete by YangShlai on 2021/5/27
 */
class HiLogManager(val config: HiLogConfig) {
    companion object{
        lateinit var instance : HiLogManager
        fun init(@NotNull config: HiLogConfig) {
            instance = HiLogManager(config)
        }
        fun get() : HiLogManager{
            return instance
        }
    }


}