package com.zziafyc.base_library.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.zziafyc.base_library.constants.Constants
import com.zziafyc.base_library.event.NetworkChangeEvent
import com.zziafyc.base_library.utils.NetWorkUtils
import com.zziafyc.base_library.utils.PreferenceUtils
import org.greenrobot.eventbus.EventBus

/**
 *
 * @author zziafyc
 * @date 2021/7/28 0028
 * @description 网络判断
 */
class NetworkChangeReceiver : BroadcastReceiver() {
    private var hasNetwork: Boolean = PreferenceUtils.getBoolean(
        Constants.HAS_NETWORK_KEY,
        true
    )

    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = NetWorkUtils.isNetworkConnected(context)
        if (isConnected) {
            if (isConnected != hasNetwork) {
                EventBus.getDefault().post(NetworkChangeEvent(isConnected))
            }
        }
    }
}

