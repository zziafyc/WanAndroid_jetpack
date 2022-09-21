package com.zziafyc.wanandroid_jetpack.ui.main.mine

import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.clickNoRepeat
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.databinding.FragmentMineBinding

/**
 *
 * @author zziafyc
 * @date 2021/8/29 0029
 * @description
 */
class MineFragment : BaseVmFragment<FragmentMineBinding>() {
    private var mineVM: MineVM? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initViewModel() {
        super.initViewModel()
        mineVM = getFragmentViewModel(MineVM::class.java)
    }

    override fun initView() {
        binding.vm = mineVM;

    }

    override fun initData() {
        mineVM?.getInternal()
    }

    override fun initListener() {
        binding.clMineSettings.clickNoRepeat {
            nav().navigate(R.id.action_main_fragment_to_settings_fragment)
        }
        binding.tvMineName.clickNoRepeat{
            nav().navigate(R.id.action_main_fragment_to_login_fragment)
        }
    }
}