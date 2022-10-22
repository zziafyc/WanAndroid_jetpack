package com.zziafyc.wanandroid_jetpack.ui.main.home

import com.zziafyc.base_library.base.BaseRepository
import com.zziafyc.wanandroid_jetpack.bean.ArticleBean
import com.zziafyc.wanandroid_jetpack.http.ApiService
import com.zziafyc.wanandroid_jetpack.http.RetrofitManager

/**
 *
 * @author zziafyc
 * @date 2021/8/9 0009
 * @description
 */
class HomeRepo : BaseRepository() {
    private var page = 0

    /**
     * 获取banner
     */
    suspend fun getBanner() = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .getBanner()
            .data()
    }

    /**
     * 获取置顶文章
     */
    suspend fun getTopArticles() = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .getTopList()
            .data()
            .let {
                ArticleBean.trans(it)
            }
    }

    /**
     * 请求第一页
     */
    suspend fun getArticles() = withIO {
        page = 0
        RetrofitManager.getApiService(ApiService::class.java)
            .getArticleList(page)
            .data()
            .datas?.let {
                ArticleBean.trans(it)
            } ?: mutableListOf()
    }

    /**
     * 加载更多
     */
    suspend fun loadMoreArticles() = withIO {
        page++
        RetrofitManager.getApiService(ApiService::class.java)
            .getArticleList(page)
            .data()
            .datas?.let {
                ArticleBean.trans(it)
            } ?: mutableListOf()
    }

    /**
     * 收藏
     */
    suspend fun collect(id: Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .collect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }

    suspend fun unCollec(id: Int) = withIO {
        RetrofitManager.getApiService(ApiService::class.java)
            .unCollect(id)
            .data(Any::class.java)
            .let {
                id
            }
    }
}