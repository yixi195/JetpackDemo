package com.ysl.project.architecture.room


/**
 * @Embedded 	用于嵌套，里面的字段同样会存储在数据库中。
 *  Craete by YangShlai on 2021/6/23
 */
class CarEmbedded {
    var _id : Int = 0
    var address : String = ""//地址
    var ymd : String = "" //年份
}