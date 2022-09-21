package com.zziafyc.wanandroid_jetpack.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.zziafyc.base_library.base.BaseViewModel

/**
 * @author zziafyc
 * @date 2022/9/21
 * @description
 */
class LoginVM : BaseViewModel() {
    private val repo by lazy {
        LoginRepo()
    }

    /**
     * 用户名
     */
    val username = ObservableField<String>().apply {
        set("")
    }

    /**
     * 密码
     */
    val password = ObservableField<String>().apply {
        set("")
    }

    /**
     * 密码是否可见
     */
    val passwordVisibility = ObservableField<Boolean>().apply {
        set(false)
    }

    /**
     * 登录
     */
    val loginLiveData = MutableLiveData<UserBean>()

    fun login() {
        launch {
            loginLiveData.value = repo.login(username.get()!!, password.get()!!)
        }
    }


}