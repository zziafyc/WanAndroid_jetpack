package com.zziafyc.wanandroid_jetpack.ui.login

import androidx.lifecycle.Observer
import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.clickNoRepeat
import com.zziafyc.base_library.common.extend.toast
import com.zziafyc.base_library.utils.KeyBoardUtil
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.databinding.FragmentLoginBinding

class LoginFragment : BaseVmFragment<FragmentLoginBinding>() {
    private var loginVM: LoginVM? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_login;
    }

    override fun initViewModel() {
        super.initViewModel()
        loginVM = getFragmentViewModel(LoginVM::class.java)
    }

    override fun initView() {
        binding.vm = loginVM
    }

    override fun initData() {

    }

    override fun observer() {
        super.observer()
        loginVM?.loginLiveData?.observe(this, Observer {
            toast("登陆成功")
            nav().navigateUp()
        })
        loginVM?.errorLiveData?.observe(this, Observer {
            toast("登陆失败,错误码：" + it.errorCode + ",错误信息：" + it.errorMessage)
        })
    }

    override fun initListener() {
        super.initListener()
        binding.ivLoginClear.clickNoRepeat {
            loginVM?.username?.set("")
        }
        binding.ivLoginPasswordVisibility.clickNoRepeat {
            loginVM?.passwordVisibility?.set(!loginVM?.passwordVisibility?.get()!!)
        }
        binding.rlLoginLogin.clickNoRepeat {
            //关闭软键盘
            KeyBoardUtil.closeKeyboard(binding.editLoginUsername, mActivity)
            KeyBoardUtil.closeKeyboard(binding.editLoginPassword, mActivity)
            if (loginVM?.username?.get()!!.isEmpty()) {
                toast("请填写用户名！")
                return@clickNoRepeat

            }
            if (loginVM?.password?.get()!!.isEmpty()) {
                toast("请填写密码！")
                return@clickNoRepeat

            }
            //开始登录
            loginVM?.login()
        }

    }
}