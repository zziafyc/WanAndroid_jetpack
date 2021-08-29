package com.zziafyc.wanandroid_jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zziafyc.base_library.BaseApp
import com.zziafyc.base_library.base.BaseVmActivity

/**
 *
 * @author zziafyc
 * @date 2021/7/21 0021
 * @description
 */

class MainActivity : BaseVmActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init(savedInstanceState: Bundle?) {
        initView()
        initData()
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}