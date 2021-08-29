package com.zziafyc.base_library.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.zziafyc.base_library.navigation.NavHostFragment
import com.zziafyc.base_library.utils.ParamUtils

/**
 *
 * @author zziafyc
 * @date 2021/8/6 0006
 * @description
 */
abstract class BaseVmFragment<BD : ViewDataBinding> : BaseFragment() {
    private var activityProvider: ViewModelProvider? = null
    private var fragmentProvider: ViewModelProvider? = null
    protected lateinit var binding: BD
    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        mActivity = context as AppCompatActivity
        // 必须要在Activity与Fragment绑定后，因为如果Fragment可能获取的是Activity中ViewModel
        // 必须在onCreateView之前初始化viewModel，因为onCreateView中需要通过ViewModel与DataBinding绑定
        initViewModel()
        ParamUtils.initParam(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //由于同一个fragment对象可能被activity attach多次(比如viewPager+PagerStateAdapter中)
        //所以fragmentViewModel不能放在onCreateView初始化，否则会产生多个fragmentViewModel
        initFragmentViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutId()?.let {
            //获取viewBinding
            binding = DataBindingUtil.inflate(inflater, it, container, false)
            //将ViewDataBinding生命周期与Fragment绑定
            binding.lifecycleOwner = viewLifecycleOwner
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        observer()
        initListener()

    }

    /**
     * 初始化入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     *初始化viewModel
     */
    open fun initViewModel() {}

    open fun initFragmentViewModel() {}

    /**
     * 注册观察者
     */
    open fun observer() {}

    /**
     * 通过activity获取viewModel，跟随activity生命周期
     */
    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T? {
        if (activityProvider == null) {
            activityProvider = ViewModelProvider(this)
        }
        return activityProvider?.get(modelClass)
    }

    /**
     * 通过fragment获取viewModel，跟随fragment生命周期
     */
    protected fun <T : ViewModel> getFragmentViewModel(modelClass: Class<T>): T? {
        if (fragmentProvider == null) {
            fragmentProvider = ViewModelProvider(this)
        }
        return fragmentProvider!!.get(modelClass)

    }

    /**
     * fragment跳转
     */
    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

}