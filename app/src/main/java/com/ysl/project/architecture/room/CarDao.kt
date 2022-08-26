package com.ysl.project.architecture.room

import androidx.room.*


/**
 * 第二步：增删改查操作
 *  Craete by YangShlai on 2021/6/22
 */
@Dao
interface CarDao {
    //增  如果冲突--替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetCar(vararg car: Car?)

    //查询所有的car
    @Query("SELECT * FROM cars")
    fun getAll() : List<Car>

    //查询指定品牌的car
    @Query("SELECT * FROM cars WHERE _brand == :brand")
    fun getCarByBrand(brand : String) : List<Car>

//    //查询价格在min和max的car
//    @Query("SELECT * FROM cars WHERE _price BETWEEN :min AND :max")
//    fun getCarBetweenPrices(min : Long,max : Long)

    //可以返回void 返回int代表删除了多少行
    @Delete
    fun deleteCar(car: Car?): Int

    //更新 返回更新的个数
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updataCars(vararg car : Car?) : Int

}