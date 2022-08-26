package com.ysl.project.home.activity

import android.os.Build
import android.view.View
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.fastframe.utils.Logger
import com.ysl.project.R
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

/**
 *  线程同步
 *
 *  Craete by YangShlai on 2021/7/27
 */
class ThreadTestActivity : BaseActivity() {
//    Kotlin 中有多种实现方式可供选择，本文将所有这些方式做了整理：
//    1.Thread.join
//    2.Synchronized
//    3.ReentrantLock
//    4.BlockingQueue
//    5.CountDownLatch
//    6.CyclicBarrier
//    7.CAS
//    8.Future
//    9.CompletableFuture
//    10.Rxjava
//    11.Coroutine
//    12.Flow

    //测试task
    val task1: () -> String = {
        sleep(2000)
        "Hello".also { Logger.i(TAG,"task1 finished: $it") }
    }

    val task2: () -> String = {
        sleep(2000)
        "World".also { Logger.i(TAG,"task2 finished: $it") }
    }

    val task3: (String, String) -> String = { p1, p2 ->
        sleep(2000)
        "$p1 $p2".also { Logger.i(TAG,"task3 finished: $it") }
    }

    override fun getContentView(): Int = R.layout.activity_thread_test

    override fun initView() {


    }

    override fun initData() {
    }

    //Kotlin 兼容 Java，Java 的所有线程工具默认都可以使用。其中最简单的线程同步方式就是使用 Thread 的 join()
    fun test_join(view : View) {
        lateinit var s1: String
        lateinit var s2: String
        val t1 = Thread { s1 = task1() }
        val t2 = Thread { s2 = task2() }
        t1.start()
        t2.start()
        t1.join()
        t2.join()
        task3(s1, s2)
    }

    //使用 synchronized 锁进行同步
    fun test_synchrnoized(view : View) {
        lateinit var s1: String
        lateinit var s2: String
        Thread {
            synchronized(Unit) {
                s1 = task1()
            }
        }.start()
        s2 = task2()
        synchronized(Unit) {
            task3(s1, s2)
        }
    }

    //ReentrantLock 是 JUC 提供的线程锁，可以替换 synchronized 的使用
    fun test_ReentrantLock(view : View) {

        lateinit var s1: String
        lateinit var s2: String

        val lock = ReentrantLock()
        Thread {
            lock.lock()
            s1 = task1()
            lock.unlock()
        }.start()
        s2 = task2()

        lock.lock()
        task3(s1, s2)
        lock.unlock()

    }

    //阻塞队列内部也是通过 Lock 实现的，所以也可以达到同步锁的效果。
    fun test_blockingQueue(view : View) {
        lateinit var s1: String
        lateinit var s2: String

        val queue = SynchronousQueue<Unit>()

        Thread {
            s1 = task1()
            queue.put(Unit)
        }.start()

        s2 = task2()

        queue.take()
        task3(s1, s2)
    }

    //ReentrantLock 就是一种独享锁。相比之下，共享锁更适合本场景。例如 CountDownLatch，它可以让一个线程一直处于阻塞状态，直到其他线程的执行全部完成：
    fun test_countdownlatch(view : View) {
        lateinit var s1: String
        lateinit var s2: String
        val cd = CountDownLatch(2)  //必须调用的次数 计数
        Thread() {
            s1 = task1()
            cd.countDown() //第一次 -1
        }.start()

        Thread() {
            s2 = task2()
            cd.countDown() //第二次 -1
        }.start()

        cd.await()  //导致当前线程等待，直到锁定倒数到0
        task3(s1, s2)
    }

    //CyclicBarrier 是 JUC 提供的另一种共享锁机制，它可以让一组线程到达一个同步点后再一起继续运行，
    // 其中任意一个线程未达到同步点，其他已到达的线程均会被阻塞。
    //与 CountDownLatch 的区别在于 CountDownLatch 是一次性的，而 CyclicBarrier 可以被重置后重复使用，
    // 这也正是 Cyclic 的命名由来，可以循环使用。
    fun test_CyclicBarrier(view : View) {
        lateinit var s1: String
        lateinit var s2: String
        val cb = CyclicBarrier(3) //必须调用的线程数
        Thread {
            s1 = task1()
            cb.await() //等待线程到达
        }.start()

        Thread() {
            s2 = task1()
            cb.await() //等待线程到达
        }.start()

        cb.await() //等待最后一个线程到达
        task3(s1, s2)

    }

    //AQS 内部通过自旋锁实现同步，自旋锁的本质是利用 CompareAndSwap 避免线程阻塞的开销。因此，我们可以使用基于 CAS 的原子类计数，达到实现无锁操作的目的。
    fun test_cas(view : View) {
        lateinit var s1: String
        lateinit var s2: String
        val cas = AtomicInteger(2)
        Thread {
            s1 = task1()
            cas.getAndDecrement()
        }.start()

        Thread {
            s2 = task2()
            cas.getAndDecrement()
        }.start()

        while (cas.get() != 0) {
        }
        task3(s1, s2)
    }

    //Future  ，可以在任务执行结束时返回结果。 会堵塞线程
    fun test_future(view : View) {
        val future1 = FutureTask(Callable(task1))
        val future2 = FutureTask(Callable(task2))
        Executors.newCachedThreadPool().execute(future1)
        Executors.newCachedThreadPool().execute(future2)
        task3(future1.get(), future2.get())  // future.get()等待同步结果返回
    }

    //CompletableFuture  ，他实现了 Future 接口的同时实现了 CompletionStage 接口。
    // CompletableFuture 可以针对多个 CompletionStage 进行逻辑组合、实现复杂的异步编程。
    // 这些逻辑组合的方法以回调的形式避免了线程阻塞：
    fun test_CompletableFuture(view : View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            CompletableFuture.supplyAsync(task1)
                .thenCombine(CompletableFuture.supplyAsync(task2)) { p1, p2 ->
                    task3(p1, p2)
                }.join()
        }
    }

    //rxjava: zip 操作符可以组合两个 Observable的结果；subscribeOn 用来启动异步任务。
    fun test_Rxjava(view : View) {
        Observable.zip(
            Observable.fromCallable(Callable(task1))
                .subscribeOn(Schedulers.newThread()),
            Observable.fromCallable(Callable(task2))
                .subscribeOn(Schedulers.newThread()),
            BiFunction(task3) //压缩结果 基于多个输入值计算值的函数式接口(回调)
        ).test() //创建一个TestObserver并订阅
            .awaitTerminalEvent() //等待直到接收到任何终端事件
    }

    //Coroutine
    fun test_coroutine(view : View) {
        runBlocking {
            val c1 = async(Dispatchers.IO) {
                task1()
            }
            val c2 = async(Dispatchers.IO) {
                task2()
            }
            task3(c1.await(), c2.await())
        }
    }

    //Flow
    fun test_flow(view : View) {
        val flow1 = flow<String> { emit(task1()) }
        val flow2 = flow<String> { emit(task2()) }
        runBlocking {
            flow1.zip(flow2) { t1, t2 ->
                task3(t1, t2)
            }.flowOn(Dispatchers.IO)  //flowOn 使得 Task 在异步计算并发射结果。
                .collect()
        }
    }

}