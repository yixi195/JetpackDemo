package com.ysl.project.architecture.room

/**
 *  Craete by YangShlai on 2021/6/22
 */
interface ICarMode {
    fun addItem(car : Car)

    fun delItem(car : Car) : Int

    fun queryAll() : List<Car>?
}