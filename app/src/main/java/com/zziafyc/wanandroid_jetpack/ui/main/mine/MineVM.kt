package com.zziafyc.wanandroid_jetpack.ui.main.mine

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.zziafyc.base_library.base.BaseViewModel
import com.zziafyc.base_library.utils.SharePreferenceUtils
import com.zziafyc.wanandroid_jetpack.constants.Constants
import com.zziafyc.wanandroid_jetpack.utils.CacheUtil

/**
 * des 我的
 * @date 2020/7/10
 * @author zs
 */
class MineVM : BaseViewModel() {

    /**
     * 用户名
     */
    val username = ObservableField<String>().apply {
        set("请先登录")
    }

    /**
     * id
     */
    val id = ObservableField<String>().apply {
        set("---")
    }

    /**
     * 排名
     */
    val rank = ObservableField<String>().apply {
        set("0")
    }

    /**
     * 当前积分
     */
    val internal = ObservableField<String>().apply {
        set("0")
    }

    private val repo by lazy { MineRepo() }
    private val internalLiveData = MutableLiveData<IntegralBean>()

    fun getInternal() {
        launch {
            var integralBean: IntegralBean? = null
            SharePreferenceUtils.getObject(Constants.INTEGRAL_INFO)?.let {
                //先从本地获取积分，获取不到再通过网络获取
                integralBean = it as IntegralBean?
            }
            if (integralBean == null) {
                if (CacheUtil.isLogin()) {
                    val data = repo.getIntegral()
                    setIntegral(data)
                    SharePreferenceUtils.setObject(Constants.INTEGRAL_INFO, data)
                }
            } else {
                setIntegral(integralBean)
            }
        }
    }

    /**
     * 每次重新登录了之后，积分信息会重新获取
     */
    fun getInternalFromNetwork() {
        launch {
            val data = repo.getIntegral()
            setIntegral(data)
            SharePreferenceUtils.setObject(Constants.INTEGRAL_INFO, data)
        }
    }

    private fun setIntegral(integralBean: IntegralBean?) {
        integralBean?.let { it ->
            //通过dataBinDing与View绑定
            username.set(it.username)
            id.set("${it.userId}")
            rank.set("${it.rank}")
            internal.set("${it.coinCount}")
            internalLiveData.value = it
        }
    }

}