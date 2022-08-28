package com.zziafyc.wanandroid_jetpack.ui.settings

import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.clickNoRepeat
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.databinding.FragmentSettingsBinding

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


    override fun initListener() {
        super.initListener()
        binding.ivSettingBack.clickNoRepeat {
            nav().navigateUp()
        }
    }
}