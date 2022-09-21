package com.zziafyc.wanandroid_jetpack.utils

import com.zziafyc.base_library.utils.SharePreferenceUtils
import com.zziafyc.wanandroid_jetpack.constants.Constants
import com.zziafyc.wanandroid_jetpack.event.LoginEvent
import com.zziafyc.wanandroid_jetpack.event.LogoutEvent
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * des 缓存工具类
 * @date 2020/7/9
 * @author zs
 */
class CacheUtil {

    companion object {
        /**
         * 登录状态
         */
        fun isLogin(): Boolean {
            return SharePreferenceUtils.getBoolean(Constants.LOGIN, false)
        }

        /**
         * 退出登录，重置用户状态
         */
        fun resetUser() {
            //发送退出登录消息
            EventBus.getDefault().post(LogoutEvent())
            //更新登陆状态
            SharePreferenceUtils.setBoolean(Constants.LOGIN, false)
            //移除用户信息
            SharePreferenceUtils.removeKey(Constants.USER_INFO)
            //移除积分信息
            SharePreferenceUtils.removeKey(Constants.INTEGRAL_INFO)
        }

        /**
         * 登录
         */
        fun setLogin(data: Any?) {
            //登陆成功保存用户信息，并发送消息
            SharePreferenceUtils.setObject(Constants.USER_INFO, data)
            //更改登陆状态
            SharePreferenceUtils.setBoolean(Constants.LOGIN, true)
            //发送登陆消息
            EventBus.getDefault().post(LoginEvent())
        }
    }
}