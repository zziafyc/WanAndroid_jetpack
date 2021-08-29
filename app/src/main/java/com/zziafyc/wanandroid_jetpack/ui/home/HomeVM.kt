package com.zziafyc.wanandroid_jetpack.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zziafyc.base_library.base.BaseViewModel
import com.zziafyc.wanandroid_jetpack.bean.ArticleBean
import kotlinx.coroutines.async


/**
 *
 * @author zziafyc
 * @date 2021/8/8 0008
 * @description
 */
class HomeVM : BaseViewModel() {

    private val repo by lazy { HomeRepo() }

    /**
     * 文章列表
     */
    private val _articleList = MutableLiveData<MutableList<ArticleBean>>()

    /**
     * 对外部提供只读的LiveData
     */
    val articleList: LiveData<MutableList<ArticleBean>> = _articleList

    /**
     * banner
     */
    private val _banner = MutableLiveData<MutableList<BannerBean>>()

    /**
     * 对外部提供只读的LiveData
     */
    val banner: LiveData<MutableList<BannerBean>> = _banner

    //获取banner
    fun getBanner() {
        launch {
            _banner.value = repo.getBanner()
        }
    }

    //获取首页文章，包括置顶
    fun getArticles() {
        launch {
            val list = mutableListOf<ArticleBean>()
            val topArticle = viewModelScope.async {
                repo.getTopArticles()
            }
            val articles = viewModelScope.async {
                repo.getArticles()
            }
            list.addAll(topArticle.await())
            list.addAll(articles.await())
            _articleList.value = list
        }
    }

    /**
     * 加载更多
     */
    fun loadMoreArticles() {
        launch {
            val list = _articleList.value
            list?.addAll(repo.loadMoreArticles())
            _articleList.value = list
            handleList(_articleList)
        }
    }

}
