package com.zziafyc.base_library

import android.app.Application
import android.content.Context

/**
 *
 * @author zziafyc
 * @date 2021/7/27 0027
 * @description
 */
open class BaseApp : Application() {

    companion object {
        private lateinit var baseApp: BaseApp;
        fun getContext(): Context {
            return baseApp;
        }
    }

    override fun onCreate() {
        super.onCreate()
        baseApp = this

    }


}