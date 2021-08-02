package com.zziafyc.base_library.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author zziafyc
 * @date 2021/8/2 0002
 * @description jetPack基类
 */
abstract class BaseVmActivity : BaseActivity() {
    private var mActivityProvider: ViewModelProvider? = null

    /**
     * 初始化viewModel
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

}
