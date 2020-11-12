package com.ysl.project.test.activity

import android.text.method.ScrollingMovementMethod
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.ysl.fastframe.base.activity.BaseDatabindVMActivity
import com.ysl.fastframe.base.activity.BaseVMActivity
import com.ysl.fastframe.network.Result
import com.ysl.fastframe.utils.Logger
import com.ysl.project.R
import com.ysl.project.databinding.ActivityTestBinding
import com.ysl.project.test.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.activity_test.*
import com.ysl.project.model.bean.ArticleList

/**
 * 测试页面
 * Created by YangShlai on 2019-11-12.
 */
class TestVMDatabindActivity : BaseDatabindVMActivity<ActivityTestBinding, TestViewModel>() {
    override fun providerVMClass() = TestViewModel::class.java


    override fun getContentView(): Int {
        return R.layout.activity_test
    }


    override fun startObserve() {
        super.startObserve()
        mViewModel.apply {
            mBanners.observe(this@TestVMDatabindActivity, Observer {
                it?.let {
                    tv_test.text = it.toString()
                }
            })

            uiState.observe(this@TestVMDatabindActivity, Observer {
                if (it.showProcess) showLoading("LOADING...")

                it.showError?.let { err ->
                    dismissLoading()
                    showToast(err)
                }
                it.showSuccess?.let {
                    dismissLoading()
                    showToast("成功了啊====${it}")
                    if (it is ArticleList) {
                        tv_test.text = it.toString()
                    }
                }
            })


        }
    }

    override fun initView() {
        tv_test.setMovementMethod(ScrollingMovementMethod())
        //收藏
        btn_submit.setOnClickListener {
            mViewModel.addCollectByLiveData("123", "1").observe(this, Observer {
                if (it is Result.Success) {
                    Logger.i("ysl", "Result.Success======" + it.data)
                } else if (it is Result.Error) {
                    showToast(it.errMsg)
                }
            })
        }

        //最新文章
        btn_last.setOnClickListener {
            Logger.i("ysl","当前输入框内容===${mViewModel.strObser.get().toString()}")
            for(i in 0..9){
                getNewList(1)
            }

        }


    }

    override fun initData() {
        mDataBind.testmodel = mViewModel
    }

    /**
     * 获取最新文章
     */
    fun getNewList(page : Int){
        mViewModel.getArticleList(page).observe(this, Observer<Result<ArticleList>> {
            if (it is Result.Success) {
                showToast("最新文章获取成功")
                mDataBind.test = it.data
                mViewModel.strObser.set("获取最新文章成功:${it.data.size}")
                Logger.i("ysl", "Result.Success======" + it.data.datas.size + "\n" + it.data)
            } else if (it is Result.Error) {
                showToast(it.errMsg)
                Logger.i("ysl", "Result.Erro======\n${it.errCode}|${it.errMsg}")
            }
        })
    }
}