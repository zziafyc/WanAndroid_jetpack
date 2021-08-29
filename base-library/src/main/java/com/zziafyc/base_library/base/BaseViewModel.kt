package com.zziafyc.base_library.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zziafyc.base_library.BuildConfig
import com.zziafyc.base_library.common.extend.toast
import com.zziafyc.base_library.http.ApiException

import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException



/**
 * 错误方法
 */
typealias VmError =  (e: ApiException) -> Unit

/**
 *
 * @author zziafyc
 * @date 2021/8/8 0008
 * @description 基础vm
 */
open class BaseViewModel:ViewModel() {

    /**
     * 错误信息liveData
     */
    val errorLiveData = MutableLiveData<ApiException>()

    /**
     * 无更多数据
     */
    val footLiveDate = MutableLiveData<Any>()

    /**
     * 无数据
     */
    val emptyLiveDate = MutableLiveData<Any>()

    /**
     * 处理错误
     */
    fun handleError(e: Throwable){
        val error = getApiException(e)
        toast(error.errorMessage)
        errorLiveData.postValue(error)
    }

    protected fun <T> launch(
        block:  () -> T
        , error:VmError? = null) {
        viewModelScope.launch {
            runCatching {
                block()
            }.onFailure {
                it.printStackTrace()
                getApiException(it).apply {
                    withContext(Dispatchers.Main){
                        error?.invoke(this@apply)
                        toast(errorMessage)
                    }
                }
            }
        }
    }

    protected fun <T> launch(block: suspend () -> T) {
        viewModelScope.launch {
            runCatching {
                block()
            }.onFailure {
                if (BuildConfig.DEBUG) {
                    it.printStackTrace()
                    return@onFailure
                }
                getApiException(it).apply {
                    withContext(Dispatchers.Main){
                        toast(errorMessage)
                        //统一响应错误信息
                        errorLiveData.value = this@apply
                    }
                }
            }
        }
    }

    /**
     * 捕获异常信息
     */
    private fun getApiException(e: Throwable): ApiException {
        return when (e) {
            is UnknownHostException -> {
                ApiException("网络异常", -100)
            }
            is JSONException -> {//|| e is JsonParseException
                ApiException("数据异常", -100)
            }
            is SocketTimeoutException -> {
                ApiException("连接超时", -100)
            }
            is ConnectException -> {
                ApiException("连接错误", -100)
            }
            is HttpException -> {
                ApiException("http code ${e.code()}", -100)
            }
            is ApiException -> {
                e
            }
            /**
             * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
             * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
             */
            is CancellationException -> {
                ApiException("", -10)
            }
            else -> {
                ApiException("未知错误", -100)
            }
        }
    }

    /**
     * 处理列表是否有更多数据
     */
    protected fun<T> handleList(listLiveData: LiveData<MutableList<T>>,pageSize:Int = 20){
        val listSize = listLiveData.value?.size?:0
        if (listSize % pageSize != 0){
            footLiveDate.value = 1
        }
    }
}