package com.zziafyc.base_library.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zziafyc.base_library.utils.StatusUtils

/**
 *
 * @author zziafyc
 * @date 2021/8/2 0002
 * @description jetPack基类
 */
abstract class BaseVmActivity : BaseActivity() {
    private var mActivityProvider: ViewModelProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSystemInvadeBlack()
        initViewModel()
        observe()
    }

    /**
     * 沉浸式状态
     */
    open fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, true)
    }

    /**
     * 初始化viewModel,部分简单activity可能不需要viewModel，所以用open修饰
     */
    open fun initViewModel() {}

    /**
     * 通过activity获取viewModel，跟随activity生命周期
     */
    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(modelClass)
    }

    /**
     * 注册观察者
     */
    open fun observe() {

    }

}
