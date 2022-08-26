package com.ysl.project.architecture.room

import androidx.lifecycle.viewModelScope
import com.ysl.fastframe.base.viewmodel.BaseViewModel
import com.ysl.project.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  Craete by YangShlai on 2021/6/22
 */
class RoomViewModel : BaseViewModel() {

    fun addItem(brand : String,price : Int){
        viewModelScope.launch(Dispatchers.IO) {
            var car = Car()
            car.brand = brand
            car.price = price
            car.age = price+1
            car.remark = "remake$brand"
            CarRepository.getInstance(App.insntance.applicationContext)?.addItem(car)
        }

    }

    fun queryAll() : List<Car>?{
        return CarRepository.getInstance(App.insntance.applicationContext)?.queryAll()
    }

    fun delItem(car : Car) : Int? {
        return CarRepository.getInstance(App.insntance)?.delItem(car)
    }


}