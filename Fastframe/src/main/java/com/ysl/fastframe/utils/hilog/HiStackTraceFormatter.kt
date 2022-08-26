package com.ysl.fastframe.utils.hilog

/**
 *  Craete by YangShlai on 2021/6/10
 */
class HiStackTraceFormatter : HiLogFormatter<Array<StackTraceElement>>{
    override fun format(stackTrace : Array<StackTraceElement>): String? {
        var sb = StringBuilder(128)
        if (stackTrace == null || stackTrace.isEmpty()){
            return null
        }else if (stackTrace.size == 1){
            return "\t-${stackTrace[0]}"
        }else{
            var len = stackTrace.size
            for ((i,item) in stackTrace.withIndex()){
                if (i == 0){
                    sb.append("stackTrace：\n")
                }
                if (i != len -1){
                    sb.append("\t[")
                    sb.append(item.toString())
                    sb.append("\n")
                }else{
                    sb.append("\t[")
                    sb.append(item.toString())
                }
            }
            return sb.toString()
        }
    }
}