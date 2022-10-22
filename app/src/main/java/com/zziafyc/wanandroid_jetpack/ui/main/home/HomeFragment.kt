package com.zziafyc.wanandroid_jetpack.ui.main.home

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.zziafyc.base_library.base.BaseVmFragment
import com.zziafyc.base_library.common.extend.smartConfig
import com.zziafyc.base_library.common.extend.smartDismiss
import com.zziafyc.wanandroid_jetpack.R
import com.zziafyc.wanandroid_jetpack.adapter.ArticleAdapter
import com.zziafyc.wanandroid_jetpack.databinding.FragmentHomeBinding
import com.zziafyc.wanandroid_jetpack.viewholder.BannerViewHolder

/**
 *
 * @author zziafyc
 * @date 2021/8/8 0008
 * @description
 */
class HomeFragment : BaseVmFragment<FragmentHomeBinding>() {
    private var homeVM: HomeVM? = null
    private var bannerList: MutableList<BannerBean>? = null
    private val adapter by lazy { ArticleAdapter(mActivity) }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun initViewModel() {
        super.initViewModel()
        homeVM = getActivityViewModel(HomeVM::class.java)

    }

    override fun initView() {
        binding.vm = homeVM
        binding.homeArticleRv.let {
            it.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
            //关闭更新动画
            (it.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun initData() {
        //自动刷新
        homeVM?.getBanner()
        homeVM?.getArticles()
    }

    override fun observer() {
        super.observer()
        //banner
        homeVM?.banner?.observe(this, Observer {
            bannerList = it
            initBanner()
        })
        //文章列表
        homeVM?.articleList?.observe(this, Observer {
            binding.homeSmartRefresh.smartDismiss()
            adapter.submitList(it)
        })
        //请求错误
        homeVM?.errorLiveData?.observe(this, Observer {
            binding.homeSmartRefresh.smartDismiss()
        })
    }

    override fun initListener() {
        //SmartRefreshLayout下拉刷新
        binding.homeSmartRefresh.setOnRefreshListener {
            homeVM?.getBanner()
            homeVM?.getArticles()
        }
        //SmartRefreshLayout上拉加载
        binding.homeSmartRefresh.setOnLoadMoreListener {
            homeVM?.loadMoreArticles()
        }
        binding.homeSmartRefresh.smartConfig()
        adapter.apply {
            setOnItemClickListener { i, _ ->
                //进入文章详情
                nav().navigate(
                    R.id.action_main_fragment_to_web_fragment,
                    this@HomeFragment.adapter.getBundle(i)
                )
            }
            setOnItemChildClickListener { i, view ->
                when (view.id) {
                    //收藏
//                    R.id.ivCollect -> {
//                        if (CacheUtil.isLogin()) {
//                            this@HomeFragment.adapter.currentList[i].apply {
//                                //已收藏取消收藏
//                                if (collect) {
//                                    homeVM?.unCollect(id)
//                                } else {
//                                    homeVM?.collect(id)
//                                }
//                            }
//                        } else {
//                            nav().navigate(R.id.action_main_fragment_to_login_fragment)
//                        }
//                    }
                }
            }
        }
        /* setNoRepeatClick(binding.ivAdd) {
             when (it.id) {
                 R.id.ivAdd -> nav().navigate(R.id.action_main_fragment_to_publish_fragment)
             }
         }*/
    }

    /**
     * 初始化banner
     */
    private fun initBanner() {
        binding.banner.apply {
            setPages(bannerList) {
                BannerViewHolder()
            }
            start()
        }
    }


}