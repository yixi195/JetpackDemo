package com.ysl.project.architecture.room

import android.content.Context

/**
 * Craete by YangShlai on 2021/6/22
 */
class CarRepository private constructor(context: Context) : ICarMode {
    private val carDao: CarDao
    init {
        //获取到carDao
        carDao = CarDatabase.get(context).carDao
    }
    override fun addItem(car: Car) {
        carDao.insetCar(car)
    }

    override fun delItem(car: Car): Int {
        return carDao.deleteCar(car)
    }

    override fun queryAll(): List<Car>? {
        return carDao.getAll()
    }

    companion object {
        @Volatile
        private var carRepository: CarRepository? = null
        fun getInstance(context: Context): CarRepository? {
            if (carRepository == null) {
                synchronized(CarRepository::class.java) {
                    if (carRepository == null) {
                        carRepository = CarRepository(context)
                    }
                }
            }
            return carRepository
        }
    }
}