package com.zziafyc.wanandroid_jetpack.ui.settings

import androidx.lifecycle.Observer
import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.clickNoRepeat
import com.zziafyc.base_library.common.extend.toast
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.databinding.FragmentSettingsBinding
import com.zziafyc.wanandroid_jetpack.utils.CacheUtil
import com.zziafyc.wanandroid_jetpack.utils.DialogUtils

/**
 * @author zziafyc
 * @date 2022/8/28
 * @description
 */
class SettingsFragment : BaseVmFragment<FragmentSettingsBinding>() {
    private var settingsVM: SettingsVM? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_settings
    }

    override fun initViewModel() {
        super.initViewModel()
        settingsVM = getFragmentViewModel(SettingsVM::class.java)
    }

    override fun initView() {
        binding.vm = settingsVM
    }

    override fun initData() {
    }

    override fun observer() {
        super.observer()
        settingsVM?.logoutLiveData?.observe(this, Observer {
            toast(resources.getString(R.string.have_logout))
        })

    }

    override fun initListener() {
        super.initListener()
        binding.ivSettingBack.clickNoRepeat {
            nav().navigateUp()
        }
        binding.tvSettingsLogout.clickNoRepeat {
            if (!CacheUtil.isLogin()) {
                toast(resources.getString(R.string.login_first_please))
                return@clickNoRepeat
            }

            DialogUtils.confirm(mActivity, resources.getString(R.string.makeSure_logout)) {
                settingsVM?.logout()
            }

        }
    }
}