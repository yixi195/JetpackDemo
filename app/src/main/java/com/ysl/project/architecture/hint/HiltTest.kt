package com.ysl.project.architecture.hint

import com.ysl.fastframe.utils.Logger
import javax.inject.Inject

/**
 * @Inject声明了一下，然后就可以调用它的方法了 注入到别处
 *  Craete by YangShlai on 2021/6/10
 */
class HiltTest @Inject constructor() {
    fun print(){
        Logger.i("hilt","xxxxxxxx")
    }

    fun print(content : String) : String{
        var str = "HintTest print:$content"
//        str += "\n" + iHiltTest.walking()
//        str += "\n" + iHiltTest.honking()
        Logger.i("hilt",str)
        return str
    }
}