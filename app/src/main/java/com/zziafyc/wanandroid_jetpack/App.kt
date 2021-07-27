package com.zziafyc.wanandroid_jetpack

import androidx.multidex.MultiDex
import com.zziafyc.base_library.BaseApp

class App : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

}