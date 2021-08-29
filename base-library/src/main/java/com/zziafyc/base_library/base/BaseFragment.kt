package com.zziafyc.base_library.base

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 *
 * @author zziafyc
 * @date 2021/8/4 0004
 * @description
 */
abstract class BaseFragment : Fragment() {
    /**
     * 初始化布局
     */
    protected abstract fun getLayoutId(): Int?

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
}