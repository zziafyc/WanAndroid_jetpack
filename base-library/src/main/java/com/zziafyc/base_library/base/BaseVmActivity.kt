package com.zziafyc.base_library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zziafyc.base_library.utils.ColorUtils
import com.zziafyc.base_library.utils.StatusUtils

/**
 *
 * @author zziafyc
 * @date 2021/8/2 0002
 * @description jetPack基类
 */
abstract class BaseVmActivity : AppCompatActivity() {
    private var mActivityProvider: ViewModelProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setStatusColor()
        setSystemInvadeBlack()
        initViewModel()
        observe()
        init(savedInstanceState)
        initListener()
    }

    /**
     * 设置状态栏背景颜色
     */
    open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#00ffffff"))
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
    open fun initViewModel() {

    }

    /**
     * 注册观察者
     */
    open fun observe() {

    }

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
     * 初始化入口
     */
    open fun init(savedInstanceState: Bundle?) {
        initView()
        initData()
    }

    /**
     * 初始化View以及事件
     */
    abstract fun initView()

    /**
     * 加载数据
     */
    abstract fun initData()

    /**
     * 点击事件
     */
    open fun initListener() {

    }

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId(): Int

}
