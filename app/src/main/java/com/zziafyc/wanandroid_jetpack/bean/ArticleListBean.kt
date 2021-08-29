package com.zziafyc.wanandroid_jetpack.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.zziafyc.wanandroid_jetpack.constants.Constants

/**
 *
 * @author zziafyc
 * @date 2021/8/29 0029
 * @description
 */
class ArticleListBean {
    var curPage = 0
    var offset = 0
    var over = false
    var pageCount = 0
    var size = 0
    var total = 0
    var datas: MutableList<DatasBean>? = null

    class DatasBean() : MultiItemEntity {
        override fun getItemType(): Int {
            return if (envelopePic.isNullOrEmpty()) {
                Constants.ITEM_ARTICLE
            } else {
                Constants.ITEM_ARTICLE_PIC
            }
        }

        var apkLink: String? = null
        var audit = 0
        var author: String? = null
        var canEdit = false
        var chapterId = 0
        var chapterName: String? = null
        var collect = false
        var courseId = 0
        var desc: String? = null
        var descMd: String? = null
        var envelopePic: String? = null
        var fresh = false
        var id = 0
        var link: String? = null
        var niceDate: String? = null
        var niceShareDate: String? = null
        var origin: String? = null
        var prefix: String? = null
        var projectLink: String? = null
        var publishTime: Long = 0
        var selfVisible = 0
        var shareDate: Long = 0
        var shareUser: String? = null
        var superChapterId = 0
        var superChapterName: String? = null
        var title: String? = null
        var type = 0
        var userId = 0
        var visible = 0
        var zan = 0
        var tags: List<*>? = null
    }
}