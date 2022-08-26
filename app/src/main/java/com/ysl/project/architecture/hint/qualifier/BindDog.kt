package com.ysl.project.architecture.hint.qualifier

import javax.inject.Qualifier

/**
 *  Qualifier注解的作用就是给相同类型的类或接口注入不同的实例
 *  Craete by YangShlai on 2021/6/10
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindDog

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindSnake


