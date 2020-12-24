package com.ysl.project.coroutines

import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.lifecycleScope
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.project.R
import kotlinx.android.synthetic.main.activity_coroutines_test.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * 协程测试
 * Created by YangShlai on 2020/12/18.
 */
class CoroutinesTestAcativity : BaseActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_coroutines_test
    }

    override fun initView() {
        tv_msg.setMovementMethod(ScrollingMovementMethod.getInstance())
        btn_launch.setOnClickListener {
            //lifecycleScope 与当前 Activity 的生命周期一致
            lifecycleScope.launch {
                addLog("---开始执行1---")
                var startTime = System.currentTimeMillis()
                //async变为了并行
                val one = async { getResult(2) }
                val two = async { getResult(3) }
                var endTime = System.currentTimeMillis()
                addLog("---执行结束1---one+two=${one.await() + two.await()} ---执行耗时:${endTime - startTime}")

                //默认串行
                addLog("---开始执行2---")
                var startTime2 = System.currentTimeMillis()
                val one2 = getResult(2)
                val two2 = getResult(3)
                var endTime2 = System.currentTimeMillis()
                addLog("---执行结束2---one2+two2=${one2 + two2} ---执行耗时:${endTime2 - startTime2}")
            }
        }

        val flow = (20..25).asFlow().flowOn(Dispatchers.Main)
        //延迟发射
        val flow2 = (40..45).asFlow()
            .onStart { delay(2000) }.flowOn(Dispatchers.IO)

        //Flow
        btn_flow.setOnClickListener {
            lifecycleScope.launch {
                createFlow()
                    .flowOn(Dispatchers.IO) // 将数据发射的操作放到 IO 线程中的协程
                    .catch { //异常捕获
                        addLog("catch===${it.message}")
                    }
                    .onCompletion { //完成操作
                        addLog("==onCompletion==")
                    }
                    .buffer(3) //数据发射并发，collect 不并发 int并发数量
//                    .conflate() //发射数据太快，只处理最新发射的
                    .collect { //具体的消费处理
                        addLog("num->${it}")
                    }

                addLog("--------冷流测试---------")
                //冷流：有点类似于懒加载，当我们触发 collect 方法的时候，数据才开始发射
                flow
                    .take(3) //后面跟 Int 类型的参数，表示接收多少个 emit 出的值
                    .collect {
                        addLog("flow-->${it}")
                    }

                addLog("--------有序测试---------")
                //有序：emit发射->filter条件->map转换和 collect消费 这一套完整的处理流程后，下个数据才会开始处理
                flow {
                    for (i in 1..4) {
                        addLog("$i emit")
                        emit(i)
                    }
                }.onStart { //发射前执行
                    addLog("onStart")
                }.onEach { //发射后的时候可以执行的一段代码
                    addLog("$it onEach")
                }.filter { //过滤条件  不满足的则终止不会进入下一流程
                    addLog("$it filter")
                    it % 2 != 0
                }.map { //转换处理
                    addLog("$it map")
                    "${it * it} money"
                }.collect {
                    addLog("i get $it")
                }

                addLog("--------取消测试---------")
                //取消：Flow 采用和协程一样的协作取消，也就是说，Flow 的 collect 只能在可取消的挂起函数中挂起的时候取消，否则不能取消。
                var f = flow {
                    for (i in 1..3) {
                        delay(500)
                        addLog("emit $i")
                        emit(i)
                    }
                }
                withTimeoutOrNull(1600) {
                    f.collect {
                        delay(500)
                        addLog("consume $it")
                    }
                }
                addLog("cancel finish")


                addLog("--------组合操作符 zip---------")
                //组合两个流，双方都有新数据才会发射处理
                flow.zip(flow2) { a, b ->
                    "$a|$b"
                }.collect {
                    addLog(it)
                }


                addLog("--------组合操作符 combine---------")
                //组合两个流，在经过第一次发射以后，任意方有新数据来的时候就可以发射，另一方有可能是已经发射过的数据
                //等同于最新的相结合 flow 没有延迟所以最新为最后一个，flow2有延迟发射
                flow.combine(flow2) { a, b ->
                    "$a and $b"
                }.collect { addLog(it) }

                addLog("--------展平流操作符 flatMapConcat---------")
                //串行处理数据
                var start: Long = System.currentTimeMillis()
                flow.flatMapConcat { //在调用 flatMapConcat 后，collect 函数在收集新值之前会等待 flatMapConcat 内部的 flow 完成。
                    flow {
                        emit("$it First")
                        delay(500)
                        emit("$it Second")
                    }
                }.collect {
                    addLog("$it at ${System.currentTimeMillis() - start} ms from start")
                }

                addLog("--------展平流操作符 flatMapMerge---------")
                //flatMapMerge 是顺序调用内部代码块，并且并行地执行 collect 函数
                start = System.currentTimeMillis()
                flow.flatMapMerge {
                    flow {
                        emit("$it First")
                        delay(500)
                        emit("$it Second")
                    }
                }.collect { addLog("$it at ${System.currentTimeMillis() - start} ms from start") }

                addLog("--------展平流操作符 flatMapLatest---------")
                //当发射了新值之后，上个 flow 就会被取消
                (1..5).asFlow()
                    .onStart { start = System.currentTimeMillis() }
                    .onEach { delay(100) }
                    .flatMapLatest {
                        flow {
                            emit("$it: First")
                            delay(500)
                            emit("$it: Second")
                        }
                    }
                    .collect {
                        addLog("$it at ${System.currentTimeMillis() - start} ms from start")
                    }

            }


        }
        //通道
        btn_channel.setOnClickListener {
            lifecycleScope.launch {
                // 1. 生成一个 Channel
                var channel = Channel<Int>()
                // 2. 发送数据
                launch {
                    for (i in 1..5){
                        delay(200)
                        channel.send(i)
                    }
                    //关闭通道
                    channel.close()
                }
                // 3.接收数据
                launch {
                        for (x in channel) addLog("channel $x")
                }
            }
        }

    }

    override fun initData() {
    }

    private suspend fun getResult(num: Int): Int {
        delay(2000)
        return num * num
    }

    /**
     * 创建 Flow 流
     */
    fun createFlow(): Flow<Int> = flow {
        for (i in 1..10)
            emit(i)
    }

    fun addLog(msg: String?) {
        tv_msg.append(msg + "\n")
        val offset = tv_msg.lineCount * tv_msg.lineHeight
        if (offset > tv_msg.height) {
            tv_msg.scrollTo(0, offset - tv_msg.height)
        }
    }

}