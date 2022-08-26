package com.ysl.project.architecture.hint

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.project.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_hilt_sam.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Hint示列
 *  Craete by YangShlai on 2021/6/10
 *  在使用依赖注入的页面需要 @AndroidEntryPoint声明
 */
@AndroidEntryPoint
class HiltSamActivity : AppCompatActivity() {

    //1.不带参数的注入
    @Inject
    lateinit var hiltTest : HiltTest
    //2.带参数的注入
    @Inject
    lateinit var hintTestParams: HiltTestParams
    //4.第三方依赖注入
    @Inject
    lateinit var okHttpClient: OkHttpClient
    @Inject
    lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_sam)
        initView()
    }

    fun initView() {
        btn_no_params.setOnClickListener {
            setMsg(hiltTest.print("hilt test"))
        }

        btn_params.setOnClickListener {
            setMsg(hintTestParams.print())
        }

        //3.接口注入  4.Qualifier依赖注入
        btn_interface.setOnClickListener {
            setMsg(hintTestParams.printInjectInterface())
        }


        btn_three.setOnClickListener {
            setMsg("OKHTTP 连接超时时间===>" + okHttpClient.connectTimeoutMillis)
            setMsg("Retrofit ====>" + retrofit.baseUrl())
        }
    }


    private fun setMsg(newContent : String){
        tv_msg.append("\n${newContent}")
    }
}