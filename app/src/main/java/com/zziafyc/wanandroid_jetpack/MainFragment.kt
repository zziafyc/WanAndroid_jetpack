package com.zziafyc.wanandroid_jetpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.doSelected
import com.zziafyc.base_library.common.extend.initFragment
import com.zziafyc.wanandroid_jetpack.constants.Constants
import com.zziafyc.wanandroid_jetpack.databinding.FragmentMainBinding
import com.zziafyc.wanandroid_jetpack.ui.home.HomeFragment
import com.zziafyc.wanandroid_jetpack.ui.mine.MineFragment
import com.zziafyc.wanandroid_jetpack.ui.square.SquareFragment
import com.zziafyc.wanandroid_jetpack.ui.tab.TabFragment

/**
 *
 * @author zziafyc
 * @date 2021/8/4 0004
 * @description
 */
class MainFragment : BaseVmFragment<FragmentMainBinding>() {
    private val fragmentList = arrayListOf<Fragment>()

    /**
     * 首页
     */
    private val homeFragment by lazy { HomeFragment() }

    /**
     * 项目
     */
    private val projectFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type", Constants.PROJECT_TYPE)
            }
        }
    }

    /**
     * 广场
     */
    private val squareFragment by lazy { SquareFragment() }

    /**
     * 公众号
     */
    private val publicNumberFragment by lazy {
        TabFragment().apply {
            arguments = Bundle().apply {
                putInt("type", Constants.ACCOUNT_TYPE)
            }
        }
    }

    /**
     * 我的
     */
    private val mineFragment by lazy { MineFragment() }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(projectFragment)
            add(squareFragment)
            add(publicNumberFragment)
            add(mineFragment)
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        initView()
        initData()
    }


    override fun getLayoutId(): Int? {
        return R.layout.fragment_main
    }

    override fun initView() {
        Log.d("zziafyc", "initView: 点击了 sss ")
        //初始化viewpager2
        binding.vpHome.initFragment(childFragmentManager, fragmentList).run {
            //全部缓存,避免切换回重新加载
            offscreenPageLimit = fragmentList.size
        }

        binding.vpHome.doSelected {
            binding.bottomNav.menu.getItem(it).isChecked = true
        }
        //初始化底部导航栏
        binding.bottomNav.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> binding.vpHome.setCurrentItem(0, false)
                    R.id.menu_project -> binding.vpHome.setCurrentItem(1, false)
                    R.id.menu_square -> binding.vpHome.setCurrentItem(2, false)
                    R.id.menu_official_account -> binding.vpHome.setCurrentItem(3, false)
                    R.id.menu_mine -> binding.vpHome.setCurrentItem(4, false)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}