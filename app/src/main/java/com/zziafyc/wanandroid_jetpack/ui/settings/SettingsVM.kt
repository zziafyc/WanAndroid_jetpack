package com.zziafyc.wanandroid_jetpack.ui.settings

import androidx.lifecycle.MutableLiveData
import com.zziafyc.base_library.base.BaseViewModel

class SettingsVM : BaseViewModel() {

    private val repo by lazy { SettingsRepo() }
    val logoutLiveData = MutableLiveData<Any>()
    fun logout() {
        launch {
            logoutLiveData.value = repo.logout()
        }
    }

}