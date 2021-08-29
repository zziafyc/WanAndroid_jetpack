package com.zziafyc.base_library.http

/**
 *
 * @author zziafyc
 * @date 2021/8/8 0008
 * @description 用来封装业务错误信息
 */
class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()