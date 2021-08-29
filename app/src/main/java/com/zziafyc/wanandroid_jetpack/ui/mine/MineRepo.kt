package com.zziafyc.wanandroid_jetpack.ui.mine

import com.zziafyc.base_library.base.BaseRepository
import com.zziafyc.wanandroid_jetpack.http.ApiService
import com.zziafyc.wanandroid_jetpack.http.RetrofitManager


/**
 * des 我的
 * @date 2020/7/10
 * @author zs
 */
class MineRepo: BaseRepository() {

    suspend fun getIntegral() = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .getIntegral()
            .data()
    }

}