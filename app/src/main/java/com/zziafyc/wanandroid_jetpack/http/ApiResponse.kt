package com.zziafyc.wanandroid_jetpack.http

import com.zziafyc.base_library.http.ApiException
import java.io.Serializable

/**
 *
 * @author zziafyc
 * @date 2021/8/9 0009
 * @description
 */
class ApiResponse<T> : Serializable {
    private var data: T? = null

    /**
     * 业务信息
     */
    private var errorMsg = ""

    /**
     * 业务code
     */
    private var errorCode = 0

    fun data(): T {
        when (errorCode) {
            //请求成功
            0, 200 -> {
                return data!!
            }
            //-1001代表登录失效，需要重新登录
            -1001 -> {
                throw ApiException(errorMsg, errorCode)
            }
            //登录失败
            -1 -> {
                throw ApiException(errorMsg, errorCode)
            }
        }
        //其他错误
        throw ApiException(errorMsg, errorCode)
    }

    /**
     * 如果某些接口存在data为null的情况,需传入class对象
     * 生成空对象。避免后面做一系列空判断
     */
    fun data(clazz: Class<T>): T {
        when (errorCode) {
            //请求成功
            0, 200 -> {
                //避免业务层做null判断,通过反射将null替换为T类型空对象
                if (data == null) {
                    data = clazz.newInstance()
                }
                return data!!
            }
            //未登陆请求需要用户信息的接口
            -1001 -> {
                throw ApiException(errorMsg, errorCode)
            }
            //登录失败
            -1 -> {
                throw ApiException(errorMsg, errorCode)
            }
        }
        //其他错误
        throw ApiException(errorMsg, errorCode)
    }
}