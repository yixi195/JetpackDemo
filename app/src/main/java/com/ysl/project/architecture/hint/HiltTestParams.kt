package com.ysl.project.architecture.hint

import com.ysl.project.architecture.hint.qualifier.BindDog
import com.ysl.project.architecture.hint.qualifier.BindSnake
import javax.inject.Inject

/**
 *  Craete by YangShlai on 2021/6/10
 *  hildTest 也是被@Inject声明了 ，所以可以直接使用
 */
class HiltTestParams @Inject constructor(val hildTest: HiltTest) {
    @BindDog
    @Inject
    lateinit var dog : Animal

    @BindSnake
    @Inject
    lateinit var snake : Animal


    fun print(): String {
        return "这是一个带参数的注入：${hildTest.print("ysl")}"
    }

    /**
     * 接口注入
     */
    fun printInjectInterface(): String {
        dog.honking()
        dog.walking()

        snake.honking()
        snake.walking()
        return "printInjectInterface"
    }

//    fun printDog() : String{
//        dog.walking()
//        dog.honking()
//        return "printDog"
//    }
//
//    fun printSnake() : String{
//        snake.walking()
//        snake.honking()
//        return "printSnake"
//    }

}