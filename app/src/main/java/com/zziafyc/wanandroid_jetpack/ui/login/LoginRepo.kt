package com.zziafyc.wanandroid_jetpack.ui.login

import com.zziafyc.base_library.base.BaseRepository
import com.zziafyc.wanandroid_jetpack.http.ApiService
import com.zziafyc.wanandroid_jetpack.http.RetrofitManager
import com.zziafyc.wanandroid_jetpack.utils.CacheUtil

class LoginRepo : BaseRepository() {
    suspend fun login(username: String, password: String) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .login(username,password)
            .data()
            .apply {
                CacheUtil.setLogin(this)
            }
    }
}