package com.zziafyc.wanandroid_jetpack.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.clickNoRepeat
import com.zziafyc.base_library.utils.Param
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.databinding.FragmentWebBinding

class WebFragment : BaseVmFragment<FragmentWebBinding>() {

    private var webVM: WebVM? = null

    /**
     * 文章标题
     */
    @Param
    private var title: String? = null

    /**
     * 文章url
     */
    @Param
    private var loadUrl: String? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_web
    }

    override fun initViewModel() {
        //TODO:此处不知道为什么不是getFragmentModel
        webVM = getActivityViewModel(WebVM::class.java)

    }

    override fun initView() {
        binding.vm = webVM
        binding.tvTitle.text = title
        binding.ivWebBack.clickNoRepeat {
            nav().navigateUp()
        }
        //初始化webView
        initWebView()
        //设置返回按钮
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    //退出H5界面
                    nav().navigateUp()
                }
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        binding.webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        binding.webView.settings.loadWithOverviewMode = true

        //设置webViewClient,这样不会调用系统浏览器
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?, url: String?
            ): Boolean {
                return false
            }

        }
        binding.webView.loadUrl(loadUrl)

        //设置最大进度
        webVM?.maxProgress?.set(100)
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    webVM?.progressBarVisible?.set(true)
                } else {
                    webVM?.progressBarVisible?.set(false)
                }
                webVM?.progress?.set(newProgress)
            }
        }


    }

    override fun initData() {

    }


}