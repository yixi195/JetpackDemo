package com.ysl.project.architecture.koin.di

import com.ysl.fastframe.utils.hilog.HiLog
import com.ysl.project.architecture.DashboardViewModel
import com.ysl.project.architecture.hint.HiltTest
import com.ysl.project.model.bean.User
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  Create by YangShlai on 2022/8/25
 */
val testModule = module {
    //单例
    single { HiltTest() }

    //ViewModel
    viewModel { DashboardViewModel() }

    //每次生成实例
    factory { User() }
}