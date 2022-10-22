package com.zziafyc.wanandroid_jetpack.ui.settings

import com.zziafyc.base_library.base.BaseRepository
import com.zziafyc.wanandroid_jetpack.http.ApiService
import com.zziafyc.wanandroid_jetpack.http.RetrofitManager
import com.zziafyc.wanandroid_jetpack.utils.CacheUtil

class SettingsRepo : BaseRepository() {
    suspend fun logout() = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .logout()
            .data(Any::class.java)
            .apply {
                CacheUtil.resetUser()
            }
    }

}