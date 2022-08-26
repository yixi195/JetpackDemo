package com.ysl.project.architecture.room

import androidx.room.*

/**
 *  第一步：创建表
 *  Craete by YangShlai on 2021/6/22
 */
@Entity(tableName = "cars")
class Car {
    //主键 自增id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id : Int = 0

    @ColumnInfo(name = "_brand")
    var brand : String = ""

    @ColumnInfo(name = "_price")
    var price : Int = 0

    @ColumnInfo(name = "_age")
    var age : Int = 0

    //* @Embedded 	用于嵌套，减少表的创建，里面的字段同样会存储在数据库中。
//    @Embedded
//    var carEmbedded : CarEmbedded? = null


    //备注  不添加到数据库 忽略
    @Ignore
    var remark : String = ""

}