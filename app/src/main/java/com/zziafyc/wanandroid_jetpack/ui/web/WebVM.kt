package com.zziafyc.wanandroid_jetpack.ui.web

import androidx.databinding.ObservableField
import com.zziafyc.base_library.base.BaseViewModel

/**
 * @author zziafyc
 * @date 2022/8/17
 * @description
 */
class WebVM : BaseViewModel() {
    /**
     * webView进度
     */
    val progress = ObservableField<Int>()

    /**
     * 最大进度
     */
    val maxProgress = ObservableField<Int>()

    /**
     * progress是否隐藏
     */
    val progressBarVisible = ObservableField<Boolean>()

}