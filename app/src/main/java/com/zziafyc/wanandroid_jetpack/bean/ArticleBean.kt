package com.zziafyc.wanandroid_jetpack.bean

import android.text.Html
import android.text.TextUtils
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.zziafyc.wanandroid_jetpack.constants.Constants
import com.zziafyc.wanandroid_jetpack.ui.collect.CollectListBean

/**
 * @author zs
 * @date 2020/9/10
 */
data class ArticleBean(
    var id: Int = 0,

    /**
     * 作者
     */
    var author: String? = null,

    /**
     * 是否收藏
     */
    var collect: Boolean = false,

    /**
     * 描述信息
     */
    var desc: String? = null,

    /**
     * 图片类型，有和无
     */
    var picUrl: String? = null,

    /**
     * 链接
     */
    var link: String? = null,

    /**
     * 日期
     */
    var date: String? = null,

    /**
     * 标题
     */
    var title: String? = null,

    /**
     * 文章标签
     */
    var articleTag: String? = null,

    /**
     * 1.置顶
     */
    var topTitle: String? = null
) : MultiItemEntity {


    override fun getItemType(): Int {
        return if (picUrl.isNullOrEmpty()) {
            Constants.ITEM_ARTICLE
        } else {
            Constants.ITEM_ARTICLE_PIC
        }
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    /**
     * 将后端数据转换为本地定义的数据结构,原因有三
     *
     * 1.将适配器数据和后端隔离,避免后端调整数据牵连到适配器,本地定义的数据和适配器只与设计图保持一致
     * 2.很多情况下后端返回的数据需要我们要二次处理,要么在UI层处理，要么在数据层处理，我个人认为在数据层处理比较合适，
     *   UI层拿到数据无需处理直接渲染。但是这种情况下，数据层要组装字段必须得创建新的字段，避免混淆所以直接独立出一个类
     * 3.做diff运算时更容易操作
     */
    companion object {
        fun trans(list: MutableList<ArticleListBean.DatasBean>): MutableList<ArticleBean> {
            return list.map {
                ArticleBean().apply {
                    id = it.id
                    author = if (TextUtils.isEmpty(it.author)) {
                        it.shareUser
                    } else {
                        it.author
                    }
                    collect = it.collect
                    desc = it.desc
                    picUrl = it.envelopePic
                    link = it.link
                    date = it.niceDate
                    title = Html.fromHtml(it.title).toString()
                    articleTag = it.superChapterName
                    topTitle = if (it.type == 1) "置顶" else ""
                }
            }.toMutableList()
        }

        fun transByCollect(list: MutableList<CollectListBean.DatasBean>): MutableList<ArticleBean> {
            return list.map {
                ArticleBean().apply {
                    id = it.originId
                    author = it.author
                    collect = true
                    desc = it.desc
                    picUrl = it.envelopePic
                    link = it.link
                    date = it.niceDate
                    title = Html.fromHtml(it.title).toString()
                    articleTag = it.chapterName
                    topTitle = ""
                }
            }.toMutableList()
        }

    }
}