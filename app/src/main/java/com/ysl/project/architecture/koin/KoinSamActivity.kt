package com.ysl.project.architecture.koin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ysl.project.R
import com.ysl.project.architecture.DashboardViewModel
import com.ysl.project.architecture.hint.HiltTest
import com.ysl.project.model.bean.User
import kotlinx.android.synthetic.main.activity_koin_sam.*
import okhttp3.OkHttpClient
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit

/**
 * Koin示列
 *  Craete by YangShlai on 2022/8/25
 */
class KoinSamActivity : AppCompatActivity() {

    val hiltTest : HiltTest by inject()
    val viewModel : DashboardViewModel by viewModel()
    val user : User by inject()


    val okHttpClient : OkHttpClient by inject()
    val retrofit by inject<Retrofit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin_sam)
        initView()
    }

    fun initView() {
        btn_no_params.setOnClickListener {


        }

        btn_params.setOnClickListener {
        }

        //3.接口注入  4.Qualifier依赖注入
        btn_interface.setOnClickListener {
        }

        //第三方库注入测试
        btn_three.setOnClickListener {
            setMsg("OKHTTP 连接超时时间===>" + okHttpClient.connectTimeoutMillis)
            setMsg("Retrofit ====>" + retrofit.baseUrl())
        }

        //单例
        btn_sign.setOnClickListener {
            setMsg(hiltTest.print("single==> HiTest"))
        }
        //viewMode
        btn_viewmodel.setOnClickListener {
            setMsg("viewMode==>" + viewModel.text.value.toString())
        }
        //factory
        btn_factory.setOnClickListener {
            setMsg("factory==>" + user.name)
        }

    }


    private fun setMsg(newContent : String){
        tv_msg.append("\n${newContent}")
    }
}