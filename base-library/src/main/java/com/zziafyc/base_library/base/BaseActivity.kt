package com.zziafyc.base_library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

/**
 *
 * @author zziafyc
 * @date 2021/7/28 0028
 * @description
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init(savedInstanceState)
        initListener()
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    /**
     * 初始化布局
     */
    protected abstract fun getLayoutId(): Int

    /**
     * activity入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 初始化view
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 初始化监听器
     */
    protected abstract fun initListener()

    /**
     * 是否使用EventBus
     */
    open fun useEventBus(): Boolean = false


}