package com.zziafyc.wanandroid_jetpack.ui.main.mine

import com.zziafyc.wanandroid_jetpack.bean.ArticleListBean

/**
 *
 * @author zziafyc
 * @date 2021/8/29 0029
 * @description 我分享的文章
 */
class MyShareArticleListBean {
    /**
     * coinInfo : {"coinCount":400,"level":4,"rank":1085,"userId":36628,"username":"1**16720137"}
     * shareArticles : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12394,"link":"https://www.jianshu.com/p/b04930d2b85e","niceDate":"2020-03-13 09:21","niceShareDate":"2020-03-13 09:21","origin":"","prefix":"","projectLink":"","publishTime":1584062485000,"selfVisible":0,"shareDate":1584062485000,"shareUser":"18616720137","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Socket详解","type":0,"userId":36628,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":1}
     */
    var coinInfo: CoinInfoBean? = null
    var shareArticles: ShareArticlesBean? = null

    class CoinInfoBean {
        /**
         * coinCount : 400
         * level : 4
         * rank : 1085
         * userId : 36628
         * username : 1**16720137
         */
        var coinCount = 0
        var level = 0
        var rank = 0
        var userId = 0
        var username: String? = null

    }

    class ShareArticlesBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12394,"link":"https://www.jianshu.com/p/b04930d2b85e","niceDate":"2020-03-13 09:21","niceShareDate":"2020-03-13 09:21","origin":"","prefix":"","projectLink":"","publishTime":1584062485000,"selfVisible":0,"shareDate":1584062485000,"shareUser":"18616720137","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Socket详解","type":0,"userId":36628,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 1
         */
        var curPage = 0
        var offset = 0
        var over = false
        var pageCount = 0
        var size = 0
        var total = 0
        var datas: List<ArticleListBean.DatasBean>? = null
    }
}